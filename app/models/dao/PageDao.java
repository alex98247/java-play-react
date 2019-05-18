package models.dao;

import java.util.List;

public class PageDao {
    private int pageNumber;
    private List<Game> gameList;

    public PageDao(int pageNumber, List<Game> gameList) {

        this.pageNumber = pageNumber;
        this.gameList = gameList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
