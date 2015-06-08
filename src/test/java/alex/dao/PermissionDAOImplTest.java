package alex.dao;

import alex.config.AppConfig;
import alex.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
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
    Page testPage;
    User testUser;

    @Before
    public void setUp() throws Exception {
        Page page = new Page();
        page.setContent("Test Content");
        page.setTitle("Test Title");
        page.setPermissionType(PermissionType.READ);

        User user = new User();
        user.setName("Test User");
        user.setUserGroup(UserGroup.ADMIN);

        page.setAuthor(user);

        userDAO.saveUser(user);
        pageDAO.savePage(page);

        testUser = user;
        testPage = page;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addPermission() throws Exception {
        Permission permission = new Permission();
        permission.setPage(testPage);
        permission.setUser(testUser);
        permission.setType(PermissionType.READ);
        permissionDAO.savePermission(permission);

        Permission actualPermission = permissionDAO.getPermission(testPage.getId(), testUser.getId());

        assertThat(actualPermission, is(permission));
    }

    @Test
    public void getPagesVisibleForUser() throws Exception {

    }
}