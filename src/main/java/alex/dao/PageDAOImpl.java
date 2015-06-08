package alex.dao;

import alex.entity.Page;
import alex.entity.User;
import alex.entity.UserGroup;
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

import javax.jws.soap.SOAPBinding;
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
    public List<Page> getPagesByAuthor(int authorID) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM PAGE WHERE AUTHOR_ID = " + authorID + " UNION SELECT * FROM PAGE WHERE PERMISSION = 'EDIT' OR PERMISSION = 'READ'");;
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
