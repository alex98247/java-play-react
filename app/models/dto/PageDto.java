package models.dto;

import io.ebean.PagedList;
import models.dao.Game;

import java.util.List;

public class PageDto {
    private int pageNumber;
    private final int totalNumber;
    private List<Game> gameList;

    public PageDto(int pageNumber, PagedList<Game> pagedList) {

        this.pageNumber = pageNumber;
        this.totalNumber = pagedList.getTotalPageCount();
        this.gameList = pagedList.getList();
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

    public int getTotalNumber() { return totalNumber; }
}
