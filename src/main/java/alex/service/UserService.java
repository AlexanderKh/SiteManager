package alex.service;

import alex.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserByName(String input);

    User register(String userName);

    List<User> getUsersWithTheirPages(User testUser);

    void deleteUser(User currentUser);
}
