package models.dto;

import models.dao.Game;

import java.util.List;

public class PageDto {
    private int pageNumber;
    private List<GameDto> gameList;

    public PageDto(int pageNumber, List<GameDto> pagedList) {

        this.pageNumber = pageNumber;
        this.gameList = pagedList;
    }

    public List<GameDto> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameDto> gameList) {
        this.gameList = gameList;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

}
