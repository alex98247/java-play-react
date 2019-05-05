package tools;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import models.dao.Game;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DbGameImpl implements DbGame {

    public List<Game> getGames(int count) {
        int quotient = count / 50;
        int remainder = count - quotient * 50;
        Timestamp timestamp = new Timestamp(-631162800);

        List<Game> games;
        games = getButchGames(timestamp, remainder);

        for (int i = 0; i < quotient; i++) {
            timestamp = games.stream()
                    .map(x -> x.getCreated_at())
                    .max(Comparator.comparing(Timestamp::getTime))
                    .orElse(timestamp);
            games.addAll(getButchGames(timestamp, 50));
        }
        return games;
    }

    private List<Game> getButchGames(Timestamp timestamp, int count) {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api-v3.igdb.com/games")
                .header("user-key", "d0fc4e5aa35986706d0b32bb67d615a7")
                .header("Accept", "application/json")
                .body("fields name,popularity,created_at; limit " + count + "; where created_at > " + timestamp.getTime() + ";")
                .asJson();

        Gson googleJson = new GsonBuilder()
                .registerTypeAdapter(Timestamp.class, (JsonDeserializer<Timestamp>) (json, typeOfT, context) -> new Timestamp(json.getAsJsonPrimitive().getAsLong()))
                .registerTypeAdapter(Timestamp.class, (JsonSerializer<Timestamp>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                .create();
        Type typeToken = new TypeToken<ArrayList<Game>>() {
        }.getType();
        String jsonBody = jsonResponse.getBody().toString();
        ArrayList<Game> games = googleJson.fromJson(jsonBody, typeToken);
        games.stream().forEach(x -> x.setId(0));
        return games.stream().map(x-> new Game(0, x.getName(), x.getPopularity(), x.getCreated_at())).collect(Collectors.toList());
    }
}
