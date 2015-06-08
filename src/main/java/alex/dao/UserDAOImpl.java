package alex.dao;

import alex.entity.Page;
import alex.entity.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<User> getUsersWithTheirPages() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createCriteria(User.class).list();
        for (User user : users){
            Hibernate.initialize(user);
        }
        return users;
    }

    @Transactional
    public User getUser(String input) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("name", input)).uniqueResult();
    }

    @Transactional
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public void deleteUser(User currentUser) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(currentUser);
    }

    @Transactional
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}
