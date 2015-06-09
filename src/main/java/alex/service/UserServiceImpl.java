package alex.service;

import alex.dao.PageDAO;
import alex.dao.PermissionDAO;
import alex.dao.UserDAO;
import alex.entity.User;
import alex.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PermissionDAO permissionDAO;

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public User getUserByName(String input) {
        return userDAO.getUser(input);
    }

    public User register(String userName) {
        User user = new User();
        user.setName(userName);
        user.setUserGroup(UserGroup.USER);
        userDAO.saveUser(user);
        return user;
    }

    public void deleteUser(User currentUser) {
        userDAO.deleteUser(currentUser);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
