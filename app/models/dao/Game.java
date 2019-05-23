package models.dao;

import io.ebean.Finder;
import io.ebean.Model;
import models.dto.GameDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "games")
public class Game extends Model {
    @Id
    private long id;
    private String name;
    private double popularity;
    private Timestamp created_at;

    public Game() {
        super();
    }

    public Game(long id, String name, double popularity, Timestamp created_at){ super();
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.created_at = created_at;
    }

    public Game(GameDto game) {
        this.id = game.getId();
        this.name = game.getName();
        this.popularity = game.getPopularity();
        try {
            this.created_at = new java.sql.Timestamp(new SimpleDateFormat("dd-MM-yyyy").parse(game.getCreated_at()).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final Finder<Long, Game> find = new Finder<>(Game.class);

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}