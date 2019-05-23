package service;

import io.ebean.Ebean;
import io.ebean.Model;
import io.ebean.PagedList;
import models.dao.Game;

import java.util.List;

public class GameServiceImpl implements GameService {

    public Game getGameById(long id) {
        return null;
    }

    public List<Game> getGames() {
        return Game.find.all();
    }

    public PagedList<Game> getPage(int page, int size) {
        return Game.find.query()
                .fetch("game_id")
                .setFirstRow((page-1)*size)
                .setMaxRows(size)
                .orderBy("id")
                .findPagedList();
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
