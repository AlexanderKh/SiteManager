package alex.service;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;

import java.security.Principal;
import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserByID(int id);

    User getUserByPrincipal(Principal principal);

    User getUserByName(String name);

    List<User> searchUsersByName(String name);

    User createUser(String userName, UserGroup userGroup);

    void saveUser(User user);

    void deleteUser(User user);

    List<User> getUsersWithoutPage(Page page);
}
