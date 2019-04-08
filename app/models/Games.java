package models;

import io.ebean.Finder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "games")
public class Games {
    private long id;
    private String name;
    private double popularity;
    private int created_at;

    public Games() {
        super();
    }

    public static final Finder<Long, Games> find = new Finder<>(Games.class);

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


    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
