package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "claims")
public class Claim extends Model {
    private long id;
    private long user_id;
    private Timestamp created_at;
    private boolean solved;
    private Timestamp solved_at;
    private String comment;
    private String theme;

    public Claim() {
        super();
    }

    public Claim(long id, long user_id, Timestamp created_at, boolean solved, Timestamp solved_at, String comment) {
        this.id = id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.solved = solved;
        this.solved_at = solved_at;
        this.comment = comment;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public void setSolved_at(Timestamp solved_at) {
        this.solved_at = solved_at;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public boolean isSolved() {
        return solved;
    }

    public Timestamp getSolved_at() {
        return solved_at;
    }

    public String getComment() {
        return comment;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
