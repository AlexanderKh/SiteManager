package alex.dao;

import alex.entity.User;
import com.sun.xml.internal.ws.util.ReadAllStream;
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
    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    @Transactional
    public User getUser(String input) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("NAME", input)).uniqueResult();
    }

    @Transactional
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
