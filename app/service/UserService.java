package service;

import models.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);
    List<User> getUser();
    void deleteUser(long id);
    void createUser(User user);
    void updateUser(User user);
}
