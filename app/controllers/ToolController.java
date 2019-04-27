package controllers;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import models.AdminParams;
import models.Game;
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

    public Result addGameToDb() {
        JsonNode json = request().body().asJson();
        AdminParams game = Json.fromJson(json, AdminParams.class);
        int gamesCount = game.getGamesCount();
        List<Game> games = dbGame.getGames(gamesCount);
        gameService.createGames(games);
        return ok();
    }
}
