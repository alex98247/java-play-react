package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.dao.History;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.HistoryService;

import javax.inject.Inject;
import java.util.List;

public class HistoryController extends Controller {
    @Inject
    HistoryService historyService;

    public Result getHistory() {
        List<History> history = historyService.getHistory();
        JsonNode jsonNode = Json.toJson(history);
        return ok(jsonNode).as("application/json");
    }
}
