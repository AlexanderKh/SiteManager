package alex.service;

import alex.dao.UserDAO;
import alex.entity.User;
import alex.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

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

    public List<User> getUsersWithTheirPages() {
        return userDAO.getUsersWithTheirPages();
    }

    public void deleteUser(User currentUser) {
        userDAO.deleteUser(currentUser);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
