package alex.service;

import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUser(int id);

    User getUserByName(String name);

    User createUser(String userName, UserGroup userGroup);

    void deleteUser(User user);

    List<Permission> getPermissions(User user);

    void deletePermission(int id);
}
