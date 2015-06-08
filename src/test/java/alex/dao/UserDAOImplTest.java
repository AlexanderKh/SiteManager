package alex.dao;

import alex.config.AppConfig;
import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDAOImplTest {

    @Autowired
    PageDAO pageDAO;
    @Autowired
    UserDAO userDAO;
    Page testPage;
    User testUser;

    @Before
    public void setUp() throws Exception {
        Page page = new Page();
        page.setContent("Test Content");
        page.setTitle("Test Title");
        page.setPermission(Permission.READ);

        User user = new User();
        user.setName("Test User");
        user.setUserGroup(UserGroup.ADMIN);

        testUser = user;
        testPage = page;
    }

    @After
    public void tearDown() throws Exception {
        pageDAO.deletePage(testPage);
        userDAO.deleteUser(testUser);
    }

    @Test
    public void getUsersWithTheirPages() throws Exception {
        userDAO.saveUser(testUser);
        pageDAO.addPage(testPage);

        User actualUser = userDAO.getUser(testUser.getName());

        assertThat(actualUser.getPages(), not(is(Collections.<Page>emptyList())));
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
}