package models.dao;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Promo {
    private long id;
    private long game_id;
    private Timestamp start_data;
    private Timestamp end_data;
    private Integer discount;

    public Promo() {
        super();
    }

    public Promo(long id, long game_id, Timestamp start_data, Timestamp end_data, Integer discount) {
        this.id = id;
        this.game_id = game_id;
        this.start_data = start_data;
        this.end_data = end_data;
        this.discount = discount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGame_id(long game_id) {
        this.game_id = game_id;
    }

    public void setStart_data(Timestamp start_data) {
        this.start_data = start_data;
    }

    public void setEnd_data(Timestamp end_data) {
        this.end_data = end_data;
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

    public Timestamp getStart_data() {
        return start_data;
    }

    public Timestamp getEnd_data() {
        return end_data;
    }

    public Integer getDiscount() {
        return discount;
    }
}
