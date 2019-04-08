package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Game;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class GameController extends Controller {

    public Result addGames() {
        String json = request().body().asText();
        return ok();
    }

    public Result deleteGames(long id) {
        Game.db().delete(id);
        return ok();
    }

    public Result updateGames() {
        return ok();
    }

    public Result getGames(){
        List<Game> games = Game.find.all();
        JsonNode jsonNode = Json.toJson(games);
        return ok(jsonNode).as("application/json");
    }
}
