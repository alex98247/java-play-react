package models.dto;

import models.dao.Game;

import java.util.List;

public class PageDto {
    private int pageNumber;
    private List<Game> gameList;

    public PageDto(int pageNumber, List<Game> gameList) {

        this.pageNumber = pageNumber;
        this.gameList = gameList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
