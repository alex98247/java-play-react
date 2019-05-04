package service;

import io.ebean.Model;
import models.Game;

import java.util.List;

public class GameServiceImpl implements GameService {

    public Game getGameById(long id) {
        return null;
    }

    public List<Game> getGames() {
        return Game.find.all();
    }

    public void deleteGame(long id) {
        Game.db().delete(id);
    }

    public void createGame(Game game) {
        game.save();
    }

    public void updateGame(Game game) {
        game.update();
    }

    public void createGames(List<Game> games) {
        games.forEach(Model::save);
    }
}
