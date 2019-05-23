package models.dto;

import models.dao.Game;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

public class GameDto {
    private long id;
    private String name;
    private double popularity;
    private String created_at;

    public GameDto(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.popularity = new BigDecimal(game.getPopularity()).setScale(3, RoundingMode.UP).doubleValue();
        this.created_at = new SimpleDateFormat("dd-MM-yyyy").format(game.getCreated_at());
    }

    public long getId() {
        return id;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


}
