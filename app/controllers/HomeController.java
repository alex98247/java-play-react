package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.mvc.*;
import securesocial.core.java.SecuredAction;

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

    @SecuredAction
    public Result appSummary() {
        // Find all tasks
        List<User> users = User.find.all();
        JsonNode jsonNode = Json.toJson(new AppSummary("Java Play React Seed"));
        return ok(jsonNode).as("application/json");
    }
}
