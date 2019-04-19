package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.UserService;

import javax.inject.Inject;
import java.util.List;

public class UserController extends Controller{

    @Inject
    private UserService userService;

    public Result addUser() {
        JsonNode json = request().body().asJson();
        User user = Json.fromJson(json, User.class);
        userService.createUser(user);
        return ok();
    }

    public Result deleteUser(long id) {
        userService.deleteUser(id);
        return ok();
    }

    public Result updateUser(long id) {
        JsonNode json = request().body().asJson();
        User user = Json.fromJson(json, User.class);
        userService.updateUser(user);
        return ok();
    }

    public Result getUsers() {
        List<User> users = userService.getUser();
        JsonNode jsonNode = Json.toJson(users);
        return ok(jsonNode).as("application/json");
    }

    public Result getUserById(long id) {
        User user = userService.getUserById(id);
        JsonNode jsonNode = Json.toJson(user);
        return ok(jsonNode).as("application/json");
    }
}
