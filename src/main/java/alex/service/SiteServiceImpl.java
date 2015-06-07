package alex.service;

import alex.dao.PageDAO;
import alex.dao.UserDAO;
import alex.entity.User;
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
}
