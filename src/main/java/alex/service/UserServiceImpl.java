package alex.service;

import alex.dao.PermissionDAO;
import alex.dao.UserDAO;
import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PermissionDAO permissionDAO;

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    public List<User> searchUsersByName(String name) {
        return userDAO.searchUsersByName(name);
    }

    public User getUserByName(String name) {
        return userDAO.getUser(name);
    }

    public User createUser(String userName, UserGroup userGroup) {
        User user = new User();
        user.setName(userName);
        user.setUserGroup(userGroup);
        userDAO.saveUser(user);
        return user;
    }

    public void deleteUser(User user) {
        permissionDAO.deleteByUser(user);
        userDAO.deleteUser(user);
    }

    public List<Permission> getPermissions(User user) {
        return permissionDAO.getPermissionsByUser(user);
    }

    public void deletePermission(int id) {
        Permission permission = permissionDAO.getPermission(id);
        permissionDAO.deletePermission(permission);
    }

    public List<User> getUsersWithoutPage(Page page) {
        return userDAO.getUsersWithoutPage(page);
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public User getUserByPrincipal(Principal principal) {
        String userName = principal.getName();
        return userDAO.getUser(userName);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
