package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;

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
        return ok("aaa").as("application/json");
    }
}
