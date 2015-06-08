package alex.dao;

import alex.entity.Page;
import alex.entity.Permission;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.internal.JoinSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PermissionDAOImpl implements PermissionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void savePermission(Permission permission) {
        sessionFactory.getCurrentSession().save(permission);
    }

    @Transactional
    public Permission getPermission(int pageId, int userId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.add(Restrictions.and( Restrictions.eq("page.id", pageId), Restrictions.eq("user.id", userId) ));
        return (Permission) criteria.uniqueResult();
    }

    @Transactional
    public List<Page> getPagesVisibleForUser(int userId) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM PAGE INNER JOIN PERMISSION ON PAGE.ID = PERMISSION.PAGE_ID " +
                "WHERE PERMISSION.USER_ID = :userID AND (PERMISSION_TYPE = 'EDIT' OR PERMISSION_TYPE = 'READ')").addEntity(Page.class);
        return sqlQuery.setParameter("userID", userId).list();

    }
}
