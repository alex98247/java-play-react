package models;

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
    private long user_id;
    private long game_id;
    private Timestamp created_at;
    private boolean is_deleted;

    public Wishlist() {
        super();
    }

    public Wishlist(long id, long user_id, long game_id, Timestamp created_at, boolean is_deleted) {
        this.id = id;
        this.user_id = user_id;
        this.game_id = game_id;
        this.created_at = created_at;
        this.is_deleted = is_deleted;
    }

    public static final Finder<Long, Wishlist> find = new Finder<>(Wishlist.class);

    public void setId(long id) {
        this.id = id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
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

    public long getUser_id() {
        return user_id;
    }

    public long getGame_id() {
        return game_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }
}
