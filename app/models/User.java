package models;

import java.security.Timestamp;
import javax.persistence.*;

import io.ebean.*;

@Table(name = "users")
@Entity
public class User extends Model {
    @Id
    private long id;
    private String login;
    private Integer password_hash;
    private Timestamp created_at;
    private boolean is_deleted;

    public User() {
        super();
    }

    public User(long id, String login, Integer password_hash, Timestamp created_at, boolean is_deleted) {
        this.id = id;
        this.login = login;
        this.password_hash = password_hash;
        this.created_at = created_at;
        this.is_deleted = is_deleted;
    }

    public User getByLoginAndPassword(String login, String password) {
        try {
            User user = getByLogin(login);
            if (user == null) throw new UserNotFoundException("User with login '"+login+"' was not found");
            password = PasswordCreator.sha1Password(password, user.getSalt());
            if (!password.equals(user.getPassword())) throw new UserAuthenticationException("Password for user '"+login+"' doesn't match");
            return user;
        } catch (Exception e) {
            Logger.error("An error occurred on getting user by login and password. Login used: "+login, e);
        }
        return null;
    }

    public static final Finder<Long, User> find = new Finder<>(User.class);

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword_hash(Integer password_hash) {
        this.password_hash = password_hash;
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

    public String getLogin() {
        return login;
    }

    public Integer getPassword_hash() {
        return password_hash;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }
}
