package controllers;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import models.dto.AdminParams;
import models.dao.Game;
import org.pac4j.play.java.Secure;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.GameService;
import tools.DbGame;

import java.util.List;

public class ToolController extends Controller {

    @Inject
    DbGame dbGame;

    @Inject
    GameService gameService;

    @Secure(clients = "ParameterClient", authorizers = "admin")
    public Result addGameToDb() {
        JsonNode json = request().body().asJson();
        AdminParams game = Json.fromJson(json, AdminParams.class);
        int gamesCount = game.getGamesCount();
        List<Game> games = dbGame.getGames(gamesCount);
        gameService.createGames(games);
        List<Game> gamesFromDb = gameService.getGames();
        JsonNode jsonNode = Json.toJson(gamesFromDb);
        return ok(jsonNode).as("application/json");
    }
}
