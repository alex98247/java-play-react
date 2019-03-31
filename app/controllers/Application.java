package controllers;

import com.google.inject.Inject;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.java.Secure;
import org.pac4j.play.store.PlaySessionStore;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class Application extends Controller {

    @Inject
    private PlaySessionStore playSessionStore;


    private List<CommonProfile> getProfiles() {
        final PlayWebContext context = new PlayWebContext(ctx(), playSessionStore);
        final ProfileManager<CommonProfile> profileManager = new ProfileManager(context);
        return profileManager.getAll(true);
    }

    @Secure(clients = "OidcClient")
    public Result oidcIndex() {
        return ok(Json.toJson("alex"));
    }
}
