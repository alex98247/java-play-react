package controllers;

import ch.qos.logback.core.status.ErrorStatus;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.*;
import models.dao.Game;
import models.dto.PageDto;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.GameService;

import javax.inject.Inject;
import java.util.List;

@Api(value = "Game Controller", produces = "application/json")
public class GameController extends Controller {

    @Inject
    private GameService gameService;

    @ApiOperation(value = "Add Game", notes = "Put game from json into list")
    public Result addGame() {
        JsonNode json = request().body().asJson();
        Game game = Json.fromJson(json, Game.class);
        gameService.createGame(game);
        return ok();
    }

    @ApiOperation(value = "Delete Game", notes = "Delete game from list dy Id")
    @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)
    public Result deleteGame(@ApiParam(value = "Game Id", name = "id") long id) {
        gameService.deleteGame(id);
        return ok();
    }

    @ApiOperation(value = "Update Game", notes = "Update game in list from json")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Game Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)})
    public Result updateGame(@ApiParam(value = "Game Id", name = "id") long id) {
        JsonNode json = request().body().asJson();
        Game game = Json.fromJson(json, Game.class);
        gameService.updateGame(game);
        return ok();
    }

    @ApiOperation(value = "Get All Games", notes = "Get list of games", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Games Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)})
    public Result getGames() {
        List<Game> games = gameService.getGames();
        JsonNode jsonNode = Json.toJson(games);
        return ok(jsonNode).as("application/json");
    }

    @ApiOperation(value = "Get Page", notes = "Get page of games", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Games Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)})
    public Result getPage(int page) {
        List<Game> games = gameService.getPage(page, 15).getList();
        PageDto pageDto = new PageDto(page, games);
        JsonNode jsonNode = Json.toJson(pageDto);
        return ok(jsonNode).as("application/json");
    }

    @ApiOperation(value = "Get Game By Id", notes = "Get the game by it's Id", response = JsonNode.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Game Not Found", response = ErrorStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorStatus.class)})
    public Result getGameById(@ApiParam(value = "Game Id", name = "id") long id) {
        Game game = gameService.getGameById(id);
        JsonNode jsonNode = Json.toJson(game);
        return ok(jsonNode).as("application/json");
    }
}
