package alex.dao;

import alex.config.AppConfig;
import alex.entity.*;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDAOImplTest {
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

    @Test
    public void getUser() throws Exception {
        User actualUser = userDAO.getUser(user.getName());

        assertThat(actualUser, is(user));
    }

    @Test
    public void saveUser() throws Exception {
        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(user));
    }

    @Test
    public void deleteUser() throws Exception {
        permissionDAO.deletePermission(permission);

        userDAO.deleteUser(user);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, not(hasItem(user)));
    }

    @Test
    public void getUsers() throws Exception {
        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(user));
    }

    @Test
    public void updateUser() throws Exception{
        user.setName("Update");
        userDAO.updateUser(user);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(user));
    }
}