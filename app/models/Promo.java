package models;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Promo {
    private long id;
    private long game_id;
    private Timestamp start;
    private Timestamp end;
    private Integer discount;

    public Promo() {
        super();
    }

    public Promo(long id, long game_id, Timestamp start, Timestamp end, Integer discount) {
        this.id = id;
        this.game_id = game_id;
        this.start = start;
        this.end = end;
        this.discount = discount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public long getGame_id() {
        return game_id;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public Integer getDiscount() {
        return discount;
    }
}
