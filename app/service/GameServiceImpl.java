package service;

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

    public void createUpdateGame(Game game) {
        game.save();
    }
}
