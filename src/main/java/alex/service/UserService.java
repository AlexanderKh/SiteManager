package alex.service;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;

import java.security.Principal;
import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUser(int id);

    List<User> searchUsersByName(String name);

    User getUserByName(String name);

    User createUser(String userName, UserGroup userGroup);

    void deleteUser(User user);

    List<Permission> getPermissions(User user);

    List<User> getUsersWithoutPage(Page page);

    void saveUser(User user);

    User getUserByPrincipal(Principal principal);
}
