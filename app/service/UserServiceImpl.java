package service;

import models.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    public User getUserById(long id) {
        return null;
    }

    public List<User> getUser() {
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
