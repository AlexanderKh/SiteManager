package alex.dao;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import org.hibernate.*;
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

    @Transactional
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Transactional
    public User getUser(int id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

}
