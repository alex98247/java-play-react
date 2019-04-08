package controllers;

import models.Game;
import play.mvc.Controller;
import play.mvc.Result;
import tools.DBGame;

import java.util.List;

public class DBGameToolController extends Controller {

    public Result getGames() {

        DBGame dbGame = new DBGame();
        List<Game> games = dbGame.getGames(100);

        return ok(games.get(51).getName() + " " + games.get(0).getName());
    }
}
