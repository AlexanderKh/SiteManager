package alex.dao;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import javax.transaction.Transactional;
import java.util.List;

import static org.hibernate.criterion.Restrictions.and;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.not;

@Repository
public class PermissionDAOImpl implements PermissionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void savePermission(Permission permission) {
        sessionFactory.getCurrentSession().save(permission);
    }

    @Transactional
    public Permission getPermission(User user, Page page) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.add(and( eq("page", page), eq("user", user) ));
        return (Permission) criteria.uniqueResult();
    }

    @Transactional
    public void updatePermission(Permission permission) {
        sessionFactory.getCurrentSession().update(permission);
    }

    @Transactional
    public List<Permission> getPermissionsByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.add(eq("user", user));
        return criteria.list();
    }

    @Transactional
    public List<Permission> getPermissionsByPage(Page page) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.add(eq("page", page));
        return criteria.list();
    }

    @Transactional
    public List<Permission> getPermissions() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.addOrder(Order.asc("user"));
        return criteria.list();
    }

    @Transactional
    public void deletePermission(Permission permission) {
        sessionFactory.getCurrentSession().delete(permission);
    }

    @Transactional
    public List<Permission> getPermissionsAndUsers() {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT DISTINCT NVL(P.ID, USER.ID) AS ID, P.PAGE_ID, P.PERMISSION_TYPE, NVL(P.USER_ID, USER.ID) AS USER_ID " +
                "FROM PERMISSION P RIGHT JOIN USER ON USER.ID = P.USER_ID ORDER BY USER_ID");
        return sqlQuery.addEntity(Permission.class).list();
    }

    @Transactional
    public Permission getPermission(int id) {
        return (Permission) sessionFactory.getCurrentSession().get(Permission.class, id);
    }

    @Transactional
    public void deleteByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("DELETE FROM PERMISSION WHERE USER_ID = :userID");
        sqlQuery.setParameter("userID", user.getId()).executeUpdate();
    }

    @Transactional
    public void deleteByPage(Page page) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("DELETE FROM PERMISSION WHERE PAGE_ID= :pageID");
        sqlQuery.setParameter("pageID", page.getId()).executeUpdate();
    }


}
