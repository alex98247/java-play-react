package controllers;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import play.mvc.Controller;
import play.mvc.Result;

public class DBGameToolController extends Controller {

    public Result getGames() {

        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api-v3.igdb.com/game_videos")
                .header("user-key", "d0fc4e5aa35986706d0b32bb67d615a7")
                .header("Accept", "application/json")
                .body("fields game,name,video_id;")
                .asJson();

        return ok(jsonResponse.getBody().toString());
    }
}
