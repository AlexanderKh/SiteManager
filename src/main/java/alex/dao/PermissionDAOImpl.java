package alex.dao;

import alex.entity.Permission;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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
}
