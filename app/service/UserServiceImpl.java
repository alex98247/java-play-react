package service;

import models.dao.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    public User getUserById(long id) {
         return User.find.query().where().eq("id", id).findOne();
    }

    public User getUserByUsername(String username) {
        return User.find.query().where().eq("login", username).findOne();
    }

    public List<User> getUsers() {
        return User.find.all();
    }

    public void deleteUser(long id) {
        User.db().delete(id);
    }

    public void createUser(User user) {
        user.save();
    }

    public void updateUser(User user) {
        user.update();
    }
}
