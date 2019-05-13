package tools;

import models.Game;

import java.util.List;

public interface DbGame {
    List<Game> getGames(int count);
}
