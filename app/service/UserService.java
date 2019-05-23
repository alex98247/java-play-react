package service;

import models.dao.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);
    User getUserByUsername(String username);
    List<User> getUsers();
    void deleteUser(long id);
    void createUser(User user);
    void updateUser(User user);
}
