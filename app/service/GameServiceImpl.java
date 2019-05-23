package service;

import io.ebean.Model;
import models.dao.Game;

import java.util.List;

public class GameServiceImpl implements GameService {

    public Game getGameById(long id) {
        return  Game.find.query().where().eq("id", id).findOne();
    }

    public List<Game> getGames() {
        return Game.find.all();
    }

    public List<Game> getPage(int page, int size) {
        return Game.find.query()
                .setFirstRow((page-1)*size)
                .setMaxRows(size)
                .findList();
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
