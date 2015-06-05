package service;

import dao.PageDAO;
import dao.PermissionDAO;
import dao.UserDAO;
import dao.UserGroupDAO;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SiteServiceImpl implements SiteService {

    @Autowired
    private PageDAO pageDAO;
    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserGroupDAO userGroupDAO;


    public User getUserByName(String input) {
        User result = null;
        result = userDAO.getUsers(input);
        return result;
    }
}
