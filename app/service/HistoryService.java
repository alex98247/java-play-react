package service;

import models.dao.History;

import java.util.List;

public interface HistoryService {
    List<History> getHistory();
    void addHistory(History history);
    void updateHistory(History history);
}
