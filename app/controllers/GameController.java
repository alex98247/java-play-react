package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Games;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class GameController extends Controller {

    public Result getGames(){
        List<Games> games = Games.find.all();
        JsonNode jsonNode = Json.toJson(games);
        return ok(jsonNode).as("application/json");
    }
}
