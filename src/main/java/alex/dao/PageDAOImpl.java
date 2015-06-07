package alex.dao;

import alex.entity.Page;
import alex.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PageDAOImpl implements PageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Page> getVisiblePages(User currentUser) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM PAGE WHERE AUTHOR_ID = " + currentUser.getId() + " UNION SELECT * FROM PAGE WHERE PERMISSION = 'EDIT' OR PERMISSION = 'VIEW'");
        return sqlQuery.addEntity(Page.class).list();
    }

    @Transactional
    public Page getPage(int id) {
        return (Page) sessionFactory.getCurrentSession().get(Page.class, id);
    }

    @Transactional
    public void deletePage(Page page) {
        sessionFactory.getCurrentSession().delete(page);
    }

    @Transactional
    public void updatePage(Page page) {
        sessionFactory.getCurrentSession().update(page);
    }

    @Transactional
    public void addPage(Page page) {
        sessionFactory.getCurrentSession().save(page);
    }
}
