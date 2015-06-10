package alex.dao;

import alex.config.AppTestConfig;
import alex.entity.*;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractDAOTest {
    @Autowired
    PageDAO pageDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    PermissionDAO permissionDAO;
    @Autowired
    SessionFactory sessionFactory;
    Page page;
    User user;
    Permission permission;

    @Before
    public void setUp() throws Exception {
        user = new User("Test User", UserGroup.ADMIN);
        userDAO.saveUser(user);
        evict(user);

        page = new Page("Test Title");
        page.setContent("Test Content");
        pageDAO.savePage(page);
        evict(page);

        permission = new Permission(user, page, PermissionType.READ);
        permissionDAO.savePermission(permission);
        flush();
    }

    protected void evict(Object o) {
        sessionFactory.getCurrentSession().evict(o);
    }

    protected void flush() {
        sessionFactory.getCurrentSession().flush();
    }
}
