package service;

import models.Game;

import java.util.List;

public interface GameService {
    Game getGameById(long id);
    List<Game> getGames();
    void deleteGame(long id);
    void createUpdateGame(Game game);
}