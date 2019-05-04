package authorization;

import com.fasterxml.jackson.databind.JsonNode;
import modules.SecurityModule;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.client.finder.ClientFinder;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.Credentials;
import org.pac4j.core.engine.DefaultCallbackLogic;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;
import play.libs.Json;

import java.util.List;
import java.util.Set;

import static org.pac4j.core.util.CommonHelper.*;

public class CustomCallbackLogic<R, C extends WebContext> extends DefaultCallbackLogic<R, C> {

    @Override
    public R perform(final C context, final Config config, final HttpActionAdapter<R, C> httpActionAdapter,
                     final String inputDefaultUrl, final Boolean inputSaveInSession, final Boolean inputMultiProfile,
                     final Boolean inputRenewSession, final String client) {
        HttpAction action;
        String token = "";
        try {

            // default values
            final String defaultUrl;
            if (inputDefaultUrl == null) {
                defaultUrl = Pac4jConstants.DEFAULT_URL_VALUE;
            } else {
                defaultUrl = inputDefaultUrl;
            }
            final boolean saveInSession;
            if (inputSaveInSession == null) {
                saveInSession = true;
            } else {
                saveInSession = inputSaveInSession;
            }
            final boolean multiProfile;
            if (inputMultiProfile == null) {
                multiProfile = false;
            } else {
                multiProfile = inputMultiProfile;
            }
            final boolean renewSession;
            if (inputRenewSession == null) {
                renewSession = true;
            } else {
                renewSession = inputRenewSession;
            }
            ClientFinder clientFinder = super.getClientFinder();

            // checks
            assertNotNull("clientFinder", clientFinder);
            assertNotNull("context", context);
            assertNotNull("config", config);
            assertNotNull("httpActionAdapter", httpActionAdapter);
            assertNotBlank(Pac4jConstants.DEFAULT_URL, defaultUrl);
            final Clients clients = config.getClients();
            assertNotNull("clients", clients);

            // logic
            final List<Client> foundClients = clientFinder.find(clients, context, client);
            assertTrue(foundClients != null && foundClients.size() == 1,
                    "unable to find one indirect client for the callback: check the callback URL for a client name parameter or suffix path"
                            + " or ensure that your configuration defaults to one indirect client");
            final Client foundClient = foundClients.get(0);
            logger.debug("foundClient: {}", foundClient);
            assertNotNull("foundClient", foundClient);

            final Credentials credentials = foundClient.getCredentials(context);
            logger.debug("credentials: {}", credentials);

            final CommonProfile profile = foundClient.getUserProfile(credentials, context);
            logger.debug("profile: {}", profile);
            saveUserProfile(context, config, profile, saveInSession, multiProfile, renewSession);

            if (profile != null) {
                final JwtGenerator generator = new JwtGenerator(new SecretSignatureConfiguration(SecurityModule.JWT_SALT));
                token = generator.generate(profile);
            }
            Set<String> roles = profile.getRoles();
            JsonNode jsonNode = Json.toJson(roles);
            action = HttpAction.ok(context, "{\"token\": \"" + token + "\",\"roles\":" + jsonNode + "}");


        } catch (final RuntimeException e) {
            action = HttpAction.forbidden(context);
            return httpActionAdapter.adapt(action.getCode(), context);
        }

        return httpActionAdapter.adapt(action.getCode(), context);
    }
}
