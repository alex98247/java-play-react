package service;

import models.dao.History;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    public List<History> getHistory() {
        return History.find.all();
    }

    public void addHistory(History history) {
        history.save();
    }

    public void updateHistory(History history) {
        history.update();
    }
}
