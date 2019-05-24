package models.dto;

import authorization.Roles;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

public class UserDto {
    private long id;
    private String login;
    private String password;
    private Timestamp created_at;
    private boolean is_deleted;
    private Roles role;

    public UserDto(long id, String login, String password, Timestamp created_at, boolean is_deleted, Roles role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.created_at = created_at;
        this.is_deleted = is_deleted;
        this.role = role;
    }
}
