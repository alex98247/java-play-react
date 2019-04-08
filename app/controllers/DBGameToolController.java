package controllers;

import models.Games;
import play.mvc.Controller;
import play.mvc.Result;
import tools.DBGame;

import java.util.List;

public class DBGameToolController extends Controller {

    public Result getGames() {

        DBGame dbGame = new DBGame();
        List<Games> games = dbGame.getGames(100);

        return ok(games.get(51).getName() + " " + games.get(0).getName());
    }
}
