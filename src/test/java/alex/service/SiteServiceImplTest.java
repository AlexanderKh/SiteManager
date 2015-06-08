package alex.service;

import alex.config.AppConfig;
import alex.dao.PageDAO;
import alex.dao.PageDAOImpl;
import alex.dao.UserDAO;
import alex.dao.UserDAOImpl;
import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class SiteServiceImplTest {

    @Mock
    PageDAO pageDAO;
    @Mock
    UserDAO userDAO;
    @Autowired
    SiteServiceImpl service;

    @Before
    public void setUp() throws Exception {
        pageDAO = mock(PageDAOImpl.class);
        userDAO = mock(UserDAOImpl.class);
        service.setPageDAO(pageDAO);
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
    public void getVisiblePages() throws Exception {

    }

    @Test
    public void register() throws Exception {
        User actualUser = service.register("Test");

        assertThat(actualUser.getName(), is("Test"));
        assertThat(actualUser.getUserGroup(), is(UserGroup.USER));
    }

    @Test
    public void getPageToEdit() throws Exception {

    }

    @Test
    public void deletePage() throws Exception {

    }

    @Test
    public void changePermissionLevel() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");
        testPage.setPermission(Permission.READ);

        service.changePermissionLevel(testPage, Permission.READ);

        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void setPageContent() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");
        testPage.setPermission(Permission.READ);

        String content = "Test Content";
        service.setPageContent(testPage, content);

        assertThat(testPage.getContent(), is(content));
    }

    @Test
    public void changePageName() throws Exception {

    }

    @Test
    public void createNewPage() throws Exception {

    }

    @Test
    public void getPageToView() throws Exception {

    }

    @Test
    public void getUsersWithTheirPages() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }
}