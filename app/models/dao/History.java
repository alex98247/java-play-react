package models.dao;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "history")
@Entity
public class History extends Model {

    @Id
    private long id;
    private String username;
    private String action;

    public History(String username, String action) {
        
        this.username = username;
        this.action = action;
    }

    public static final Finder<Long, History> find = new Finder<>(History.class);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
