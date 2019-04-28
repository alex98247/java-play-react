package modules;

import authorization.Authentificator;
import authorization.CustomCallbackLogic;
import authorization.Roles;
import be.objectify.deadbolt.java.cache.HandlerCache;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import controllers.CustomAuthorizer;
import controllers.DemoHttpActionAdapter;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.CasProxyReceptor;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.client.direct.AnonymousClient;
import org.pac4j.core.config.Config;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.matching.PathMatcher;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.client.GitHubClient;
import org.pac4j.oauth.client.TwitterClient;
import org.pac4j.oidc.client.OidcClient;
import org.pac4j.oidc.config.OidcConfiguration;
import org.pac4j.play.CallbackController;
import org.pac4j.play.LogoutController;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.deadbolt2.Pac4jHandlerCache;
import org.pac4j.play.deadbolt2.Pac4jRoleHandler;
import org.pac4j.play.store.PlayCacheSessionStore;
import org.pac4j.play.store.PlaySessionStore;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import play.Configuration;
import play.Environment;

import java.io.File;

import org.pac4j.http.client.direct.DirectFormClient;
import play.cache.SyncCacheApi;
import play.mvc.Result;
import util.Utils;

public class SecurityModule extends AbstractModule {
    public final static String JWT_SALT = "12345678901234567890123456789012";

    private final Configuration configuration;

    private static class MyPac4jRoleHandler implements Pac4jRoleHandler { }

    private final String baseUrl;

    public SecurityModule(final Environment environment, final Configuration configuration) {
        this.configuration = configuration;
        this.baseUrl = configuration.getString("baseUrl");
    }

    @Override
    protected void configure() {

        bind(HandlerCache.class).to(Pac4jHandlerCache.class);
        bind(Pac4jRoleHandler.class).to(MyPac4jRoleHandler.class);

        final PlayCacheSessionStore playCacheSessionStore = new PlayCacheSessionStore(getProvider(SyncCacheApi.class));
        //bind(PlaySessionStore.class).toInstance(playCacheSessionStore);
        bind(PlaySessionStore.class).to(PlayCacheSessionStore.class);

        //callback logic
        final CustomCallbackLogic<Result, PlayWebContext> customCallbackLogic = new CustomCallbackLogic<>();

        // callback
        final CallbackController callbackController = new CallbackController();
        callbackController.setDefaultUrl("/");
        callbackController.setMultiProfile(true);
        callbackController.setRenewSession(true);
        callbackController.setCallbackLogic(customCallbackLogic);
        bind(CallbackController.class).toInstance(callbackController);

        // logout
        final LogoutController logoutController = new LogoutController();
        logoutController.setDefaultUrl("/");
        //logoutController.setDestroySession(true);
        bind(LogoutController.class).toInstance(logoutController);
    }

    @Provides
    protected FormClient provideFormClient() {
        return new FormClient(baseUrl + "/loginForm", new Authentificator());
    }

    @Provides
    protected ParameterClient provideParameterClient() {
        final ParameterClient parameterClient = new ParameterClient("token",
                new JwtAuthenticator(new SecretSignatureConfiguration(JWT_SALT)));
        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(true);
        return parameterClient;
    }

    @Provides
    protected Config provideConfig(FormClient formClient, ParameterClient parameterClient) {

        //casClient.getConfiguration().setProxyReceptor(casProxyReceptor);

        final Clients clients = new Clients(baseUrl + "/callback", formClient, parameterClient);

        final Config config = new Config(clients);
        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer<>(Roles.ADMIN.name()));
        config.addAuthorizer("custom", new CustomAuthorizer());
        config.setHttpActionAdapter(new DemoHttpActionAdapter());
        return config;
    }
}
