package alex.dao;

import alex.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsersWithTheirPages();

    User getUser(String input);

    void saveUser(User user);

    void deleteUser(User currentUser);

    List<User> getUsers();

}
