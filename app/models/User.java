package models;

import java.security.Timestamp;
import java.util.List;
import javax.persistence.*;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Role;
import be.objectify.deadbolt.java.models.Subject;
import io.ebean.*;

@Table(name = "users")
@Entity
public class User extends Model implements Subject {
    @Id
    private long id;
    private String login;
    private Integer password_hash;
    private Timestamp created_at;
    private boolean is_deleted;

    @ManyToMany
    public List<SecurityRole> roles;

    @ManyToMany
    public List<UserPermission> permissions;

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

    @Override
    public List<? extends Role> getRoles() {
        return roles;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        return permissions;
    }

    @Override
    public String getIdentifier() {
        return login;
    }

    public static User findByUserName(String userName)
    {
        return find.query().where()
                .eq("login",
                        userName).findOne();
    }
}
