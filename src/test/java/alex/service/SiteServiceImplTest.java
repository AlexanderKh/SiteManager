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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

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
        User testUser = new User();
        testUser.setName("Test User");
        testUser.setUserGroup(UserGroup.USER);
        testUser.setId(10);
        User testAdmin = new User();
        testAdmin.setUserGroup(UserGroup.ADMIN);
        testAdmin.setName("Test Admin");

        service.getVisiblePages(testUser);
        verify(pageDAO).getPagesByAuthor(testUser.getId());

        service.getVisiblePages(testAdmin);
        verify(pageDAO).getPages();
    }

    @Test
    public void register() throws Exception {
        User actualUser = service.register("Test");

        assertThat(actualUser.getName(), is("Test"));
        assertThat(actualUser.getUserGroup(), is(UserGroup.USER));
    }

    @Test
    public void getPageToEdit() throws Exception {
        User testUser = new User();
        testUser.setName("Test User");
        testUser.setUserGroup(UserGroup.USER);

        service.getPageToEdit(testUser, 0);
        verify(pageDAO).getPage(0);
    }

    @Test
    public void deletePage() throws Exception {
        Page testPage = new Page();

        service.deletePage(testPage);

        verify(pageDAO).deletePage(testPage);
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
        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void changePageName() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");
        testPage.setPermission(Permission.READ);

        String title = "Updated Title";
        service.changePageName(testPage, title);

        assertThat(testPage.getTitle(), is(title));
        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void createNewPage() throws Exception {
        service.createNewPage("Test Title", Permission.READ, new User());
        verify(pageDAO).addPage(Matchers.any(Page.class));
    }

    @Test
    public void getPageToView() throws Exception {
        User testUser = new User();
        testUser.setName("Test User");
        testUser.setUserGroup(UserGroup.USER);
        User testAdmin = new User();
        testAdmin.setUserGroup(UserGroup.ADMIN);
        testAdmin.setName("Test Admin");

        Page page = new Page();
        page.setPermission(Permission.NO);
        when(pageDAO.getPage(anyInt())).thenReturn(page);

        Page actualPage = service.getPageToView(testUser, 0);
        assertThat(actualPage, is(nullValue()));

        actualPage = service.getPageToView(testAdmin, 0);
        assertThat(actualPage, is(page));

        page.setPermission(Permission.READ);
        actualPage = service.getPageToView(testUser, 0);
        assertThat(actualPage, is(page));
    }

    @Test
    public void getUsersWithTheirPages() throws Exception {
        service.getUsersWithTheirPages();

        verify(userDAO).getUsersWithTheirPages();
    }

    @Test
    public void deleteUser() throws Exception {
        User testUser = new User();

        service.deleteUser(testUser);

        verify(userDAO).deleteUser(testUser);
    }
}