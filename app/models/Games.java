package models;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Games {
    private long id;
    private String name;
    private String company;
    private Date year;
    private Timestamp created_at;
    private boolean is_deleted;

    public Games() {
        super();
    }

    public Games(Games game) {
        this.id = game.getId();
        this.name = game.getName();
        this.company = game.getCompany();
        this.year = game.getYear();
        this.created_at = game.getCreated_at();
        this.is_deleted = game.isIs_deleted();
    }

    public Games(long id, String name, String company, Date year, Timestamp created_at, boolean is_deleted) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.year = year;
        this.created_at = created_at;
        this.is_deleted = is_deleted;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public Date getYear() {
        return year;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }
}
