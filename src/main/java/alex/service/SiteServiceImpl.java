package alex.service;

import alex.dao.PageDAO;
import alex.dao.UserDAO;
import alex.entity.Page;
import alex.entity.User;
import alex.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private PageDAO pageDAO;
    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public User getUserByName(String input) {
        return userDAO.getUser(input);
    }

    public List<Page> getPages(User currentUser) {
        return null;
    }

    public User register(String userName) {
        User user = new User();
        user.setName(userName);
        user.setUserGroup(UserGroup.USER);
        userDAO.saveUser(user);
        return user;
    }
}
