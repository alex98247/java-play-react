package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import modules.SecurityModule;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class AuthorizationController extends Controller {

    @Inject
    private PlaySessionStore playSessionStore;

    private List<CommonProfile> getProfiles() {
        final PlayWebContext context = new PlayWebContext(ctx(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.getAll(true);
    }

    public Result jwt() {
        final List<CommonProfile> profiles = getProfiles();
        final JwtGenerator generator = new JwtGenerator(new SecretSignatureConfiguration(SecurityModule.JWT_SALT));
        String token = "";
        if (CommonHelper.isNotEmpty(profiles)) {
            token = generator.generate(profiles.get(0));
        }
        JsonNode jsonNode = Json.toJson(token);
        return ok(jsonNode).as("application/json");
    }

}
