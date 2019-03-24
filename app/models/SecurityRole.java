package models;

import be.objectify.deadbolt.java.models.Role;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityRole extends Model implements Role {

    @Id
    public Long id;

    public String name;

    public static final Finder<Long, SecurityRole> find = new Finder<>(SecurityRole.class);

    @Override
    public String getName() {
        return null;
    }
}
