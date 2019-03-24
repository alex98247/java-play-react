package security;

import be.objectify.deadbolt.java.models.Role;

public enum MyRoles implements Role {
    foo,
    bar,
    hurdy;

    @Override
    public String getName() {
        return name();
    }
}
