package tools;

import models.dao.Game;

import java.util.List;

public interface DbGame {
    List<Game> getGames(int count);
}
