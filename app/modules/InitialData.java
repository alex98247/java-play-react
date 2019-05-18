package modules;

import authorization.Roles;
import models.dao.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Timestamp;
import java.util.Date;

public class InitialData {
    public InitialData() {

        User user = User.find.query().where().like("login", "admin").findOne();
        if (user == null) {
            User admin = new User();
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            admin.setCreated_at(ts);
            admin.setLogin("admin");
            admin.setRole(Roles.ADMIN);
            admin.setPassword_hash(BCrypt.hashpw("admin", BCrypt.gensalt(10)));
            admin.save();
        }
    }
}
