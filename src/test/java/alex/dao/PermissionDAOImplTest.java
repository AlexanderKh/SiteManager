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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PermissionDAOImplTest {
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
    public void deletePermission() throws Exception {
        permissionDAO.deletePermission(permission);

        List<Permission> actualPermissions = permissionDAO.getPermissions();

        assertThat(actualPermissions, not(hasItem(permission)));
    }

    @Test
    public void addPermission() throws Exception {
        Permission actualPermission = permissionDAO.getPermission(page, user);

        assertThat(actualPermission, is(permission));
    }

    @Test
    public void getPermissions() throws Exception {
        List<Permission> actualPermissions = permissionDAO.getPermissions();

        assertThat(actualPermissions, hasItem(permission));
    }

    @Test
    public void updatePermission() throws Exception {
        Permission actualPermission = permissionDAO.getPermission(page, user);

        assertThat(actualPermission, is(permission));

        permission.setType(PermissionType.EDIT);

        permissionDAO.updatePermission(permission);
        flush();
        evict(permission);

        actualPermission = permissionDAO.getPermission(page, user);

        assertThat(actualPermission.getType(), is(PermissionType.EDIT));
    }

    @Test
    public void getPermissionsByUser() throws Exception {
        List<Permission> actualPermissions = permissionDAO.getPermissionsByUser(user);

        assertThat(actualPermissions.get(0).getUser(), is(user));
    }

    @Test
    public void getPermissionsByPage() throws Exception {
        List<Permission> actualPermissions = permissionDAO.getPermissionsByPage(page);

        assertThat(actualPermissions.get(0).getPage(), is(page));
    }
}