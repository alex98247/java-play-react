package models.dao;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "wishlist")
@Entity
public class Wishlist extends Model {
    @Id
    private long id;
    private long userId;
    private long gameId;
    private Timestamp created_at;
    private boolean is_deleted;

    public Wishlist() {
        super();
    }

    public Wishlist(long id, long userId, long gameId, Timestamp created_at, boolean is_deleted) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.created_at = created_at;
        this.is_deleted = is_deleted;
    }

    public static final Finder<Long, Wishlist> find = new Finder<>(Wishlist.class);

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
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

    public long getUserId() {
        return userId;
    }

    public long getGameId() {
        return gameId;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }
}
