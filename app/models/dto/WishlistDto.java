package models.dto;

public class WishlistDto {

    private long id;
    private String game;

    public WishlistDto(long id, String game) {

        this.id = id;
        this.game = game;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
