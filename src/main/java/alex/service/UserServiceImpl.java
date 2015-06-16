package alex.service;

import alex.dao.PermissionDAO;
import alex.dao.UserDAO;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
