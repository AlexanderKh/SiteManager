package dao;

import entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    public User getUsers(String input) {
        return null;
    }
}
