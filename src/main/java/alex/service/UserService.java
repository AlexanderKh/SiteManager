package alex.service;

import alex.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserByName(String input);

    User register(String userName);

    void deleteUser(User currentUser);
}
