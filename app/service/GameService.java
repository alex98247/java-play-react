package service;

import io.ebean.PagedList;
import models.dao.Game;

import java.util.List;

public interface GameService {
    Game getGameById(long id);
    List<Game> getGames();
    PagedList<Game> getPage(int page, int size);
    void deleteGame(long id);
    void createGame(Game game);
    void updateGame(Game game);
    void createGames(List<Game> games);
}