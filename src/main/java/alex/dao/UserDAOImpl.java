package alex.dao;

import alex.entity.User;
import org.hibernate.SessionFactory;
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
        System.out.println(sessionFactory);
        System.out.println(sessionFactory.getCurrentSession());
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }
}
