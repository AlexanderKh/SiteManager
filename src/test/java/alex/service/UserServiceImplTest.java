package alex.service;

import alex.config.AppConfig;
import alex.dao.*;
import alex.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserServiceImplTest {

    @Mock
    PageDAO pageDAO;
    @Mock
    UserDAO userDAO;
    @Mock
    PermissionDAO permissionDAO;
    @Autowired
    UserServiceImpl service;

    @Before
    public void setUp() throws Exception {
        pageDAO = mock(PageDAOImpl.class);
        userDAO = mock(UserDAOImpl.class);
        service.setUserDAO(userDAO);
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getUsers() throws Exception {
        List<User> testList = new ArrayList<User>();
        when(userDAO.getUsers()).thenReturn(testList);

        List<User> actualUsers = service.getUsers();

        assertThat(actualUsers, is(testList));
    }

    @Test
    public void getUserByName() throws Exception {
        User testUser = new User();
        String testUserName = "Test";
        when(userDAO.getUser(testUserName)).thenReturn(testUser);

        User actualUser = service.getUserByName(testUserName);

        assertThat(actualUser, is(testUser));
    }


    @Test
    public void register() throws Exception {
        User actualUser = service.register("Test");

        assertThat(actualUser.getName(), is("Test"));
        assertThat(actualUser.getUserGroup(), is(UserGroup.USER));
    }

    @Test
    public void getUsersWithTheirPages() throws Exception {
        User testUser = new User();
        testUser.setName("Test User");
        testUser.setUserGroup(UserGroup.USER);
        User testAdmin = new User();
        testAdmin.setName("Test Admin");
        testAdmin.setUserGroup(UserGroup.ADMIN);

        service.getUsersWithTheirPages(testAdmin);

        verify(userDAO).getUsers();
    }

    @Test
    public void deleteUser() throws Exception {
        User testUser = new User();

        service.deleteUser(testUser);

        verify(userDAO).deleteUser(testUser);
    }

}