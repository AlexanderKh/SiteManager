package alex.service;

import alex.entity.Page;
import alex.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserByName(String input);

    User register(String userName);

    List<User> getUsersWithTheirPages();

    void deleteUser(User currentUser);
}
