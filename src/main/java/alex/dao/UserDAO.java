package alex.dao;

import alex.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();

    User getUser(String input);

    void saveUser(User user);
}