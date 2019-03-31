package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.User;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.java.Secure;
import org.pac4j.play.store.PlaySessionStore;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

class AppSummary {
    private String content;

    AppSummary(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

public class HomeController extends Controller {

    public Result appSummary() {
        // Find all tasks
        List<User> users = User.find.all();
        JsonNode jsonNode = Json.toJson(new AppSummary("Java"));
        return ok(jsonNode).as("application/json");
    }

    @Secure(clients = "OidcClient")
    public Result oidcIndex() {
        return ok("alex").as("application/json");
    }

    @Secure(clients = "GitHubClient")
    public Result facebookAdminIndex() {
        return ok("alex").as("application/json");
    }
}
