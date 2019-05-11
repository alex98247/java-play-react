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
    private long userId;
    private String action;

    public History(long userId, String action) {
        
        this.userId = userId;
        this.action = action;
    }

    public static final Finder<Long, History> find = new Finder<>(History.class);

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
