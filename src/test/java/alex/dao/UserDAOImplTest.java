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
import java.util.List;

import static java.util.Collections.emptyList;
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
    Page testPage;
    User testUser;
    Permission testPermission;

    @Before
    public void setUp() throws Exception {
        testPage = new Page();
        testPage.setContent("Test Content");
        testPage.setTitle("Test Title");

        testUser = new User();
        testUser.setName("Test User");
        testUser.setUserGroup(UserGroup.ADMIN);

    }

    @Test
    public void getUser() throws Exception {
        userDAO.saveUser(testUser);

        User actualUser = userDAO.getUser(testUser.getName());

        assertThat(actualUser, is(testUser));
    }

    @Test
    public void saveUser() throws Exception {
        userDAO.saveUser(testUser);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(testUser));
    }

    @Test
    public void deleteUser() throws Exception {
        userDAO.saveUser(testUser);
        userDAO.deleteUser(testUser);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, not(hasItem(testUser)));
    }

    @Test
    public void getUsers() throws Exception {
        userDAO.saveUser(testUser);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(testUser));
    }

    @Test
    public void updateUser() throws Exception{
        userDAO.saveUser(testUser);
        testUser.setName("Update");
        userDAO.updateUser(testUser);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(testUser));
    }
}