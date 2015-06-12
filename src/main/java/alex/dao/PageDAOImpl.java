package alex.dao;

import alex.entity.Page;
import alex.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PageDAOImpl implements PageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Page> getPages() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Page.class);
        return criteria.list();
    }

    @Transactional
    public List<Page> getPagesByUser(int userID) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT PAGE.* FROM PAGE INNER JOIN PERMISSION ON PAGE.ID = PERMISSION.PAGE_ID " +
                "WHERE PERMISSION.USER_ID = :userID");
        return sqlQuery.addEntity(Page.class).setParameter("userID", userID).list();
    }

    @Transactional
    public Page getPage(int id) {
        return (Page) sessionFactory.getCurrentSession().get(Page.class, id);
    }

    @Transactional
    public void deletePage(Page page) {
        Session session = sessionFactory.getCurrentSession();
        session.update(page);
        session.delete(page);
    }

    @Transactional
    public void updatePage(Page page) {
        Session session = sessionFactory.getCurrentSession();

        session.update(page);
    }

    @Transactional
    public void savePage(Page page) {
        Session session = sessionFactory.getCurrentSession();
        session.save(page);
    }

    @Transactional
    public List<Page> getPagesNotVisibleForUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT PAGE.* FROM PAGE LEFT JOIN PERMISSION ON PAGE.ID = PERMISSION.PAGE_ID " +
                "WHERE (PERMISSION.USER_ID <> :userID) OR (PERMISSION.USER_ID IS NULL)").addEntity(Page.class);
        return sqlQuery.setParameter("userID", user.getId()).list();
    }
}
