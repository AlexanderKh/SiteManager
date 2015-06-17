package alex.dao;

import alex.entity.Page;
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

    @Transactional
    public List<User> getUsersWithoutPage(Page page) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT USER.* FROM USER LEFT JOIN\n" +
                "(SELECT DISTINCT PERMISSION.USER_ID FROM PERMISSION WHERE PERMISSION.PAGE_ID = :pageID) AS PERM\n" +
                "ON PERM.USER_ID = USER.ID\n" +
                "WHERE PERM.USER_ID IS NULL").addEntity(User.class);
        return sqlQuery.setParameter("pageID", page.getId()).list();
    }

    @Override
    public List<User> searchUsersByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM USER WHERE NAME LIKE :name").addEntity(User.class);
        return sqlQuery.setParameter("name", "%" + name + "%").list();
    }

}
