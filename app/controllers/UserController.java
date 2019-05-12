package controllers;

import ch.qos.logback.core.status.ErrorStatus;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.*;
import models.dao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.UserService;

import javax.inject.Inject;
import java.util.List;

@Api(value = "User Controller", produces = "application/json")
public class UserController extends Controller{

    @Inject
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "Add User", notes = "Add new user from json")
    public Result addUser() {
        JsonNode json = request().body().asJson();
        User user = Json.fromJson(json, User.class);
        userService.createUser(user);
        logger.info("request to add new user: {}", user);
        return ok();
    }


    @ApiOperation(value = "Delete User", notes = "Delete the user by Id")
    @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)
    public Result deleteUser(@ApiParam(value = "User Id", name = "id") long id) {
        userService.deleteUser(id);
        return ok();
    }

    @ApiOperation(value = "Update User", notes = "Update user from json dy id")
    @ApiResponses({
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result updateUser(@ApiParam(value = "User Id", name = "id") long id) {
        JsonNode json = request().body().asJson();
        User user = Json.fromJson(json, User.class);
        userService.updateUser(user);
        return ok();
    }

    @ApiOperation(value = "Get All Users", notes = "Get list of users", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Users Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result getUsers() {
        List<User> users = userService.getUsers();
        JsonNode jsonNode = Json.toJson(users);
        return ok(jsonNode).as("application/json");
    }

    @ApiOperation(value = "Get User By Id", notes = "Get the user by it's Id", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class) })
    public Result getUserById(@ApiParam(value = "Game Id", name = "id") long id) {
        User user = userService.getUserById(id);
        JsonNode jsonNode = Json.toJson(user);
        return ok(jsonNode).as("application/json");
    }
}
