package models;

import java.sql.Date;
import java.sql.Timestamp;

public class Games {
    private long id;
    private String name;
    private double popularity;
    private int created_at;

    public Games() {
        super();
    }

    public Games(long id, String name, String company, Date year, Timestamp created_at, boolean is_deleted) {
        this.id = id;
        this.name = name;

    }

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
}
