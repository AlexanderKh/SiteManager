package alex.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PageDAOImpl implements PageDAO {
    @Autowired
    private SessionFactory sessionFactory;
}
