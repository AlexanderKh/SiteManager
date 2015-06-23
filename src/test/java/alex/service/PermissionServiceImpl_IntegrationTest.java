package alex.service;

import alex.config.AppTestConfig;
import alex.dao.PageDAO;
import alex.dao.PermissionDAO;
import alex.dao.UserDAO;
import alex.entity.*;
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
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PermissionServiceImpl_IntegrationTest {
    @Autowired
    PageDAO pageDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    PermissionDAO permissionDAO;
    @Autowired
    PermissionService service;

    @Before
    public void setUp() throws Exception {
        service.setPermissionDAO(permissionDAO);
    }

    @Test
    public void getUserPermissions() throws Exception {
        User firstUser = new User("First User", UserGroup.USER);
        userDAO.saveUser(firstUser);
        User secondUser = new User("Second User", UserGroup.USER);
        userDAO.saveUser(secondUser);
        User admin = new User("Admin", UserGroup.ADMIN);
        userDAO.saveUser(admin);
        Page page = new Page("Page");
        pageDAO.savePage(page);
        Permission firstUsersPage = new Permission(firstUser, page, PermissionType.EDIT);
        permissionDAO.savePermission(firstUsersPage);
        List<Permission> actualPermissions;

        actualPermissions = service.getUserPermissions(firstUser);
        assertThat(actualPermissions, hasItem(firstUsersPage));

        actualPermissions = service.getUserPermissions(secondUser);
        assertThat(actualPermissions, not(hasItem(firstUsersPage)));

        actualPermissions = service.getUserPermissions(admin);
        assertThat(actualPermissions, not(hasItem(firstUsersPage)));
    }
}