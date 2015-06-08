package alex.service;

import alex.config.AppConfig;
import alex.dao.PageDAO;
import alex.dao.PageDAOImpl;
import alex.dao.UserDAO;
import alex.dao.UserDAOImpl;
import alex.entity.Page;
import alex.entity.PermissionType;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class PageServiceImplTest {

    @Mock
    PageDAO pageDAO;
    @Mock
    UserDAO userDAO;
    @Autowired
    PageServiceImpl service;

    @Before
    public void setUp() throws Exception {
        pageDAO = mock(PageDAOImpl.class);
        userDAO = mock(UserDAOImpl.class);
        service.setPageDAO(pageDAO);
    }

    @After
    public void tearDown() throws Exception {

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
        testPage.setPermissionType(PermissionType.READ);

        service.changePermissionLevel(testPage, PermissionType.READ);

        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void setPageContent() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");
        testPage.setPermissionType(PermissionType.READ);

        String content = "Test Content";
        service.setPageContent(testPage, content);

        assertThat(testPage.getContent(), is(content));
        verify(pageDAO).updatePage(testPage);
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
    public void changePageName() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");
        testPage.setPermissionType(PermissionType.READ);

        String title = "Updated Title";
        service.changePageName(testPage, title);

        assertThat(testPage.getTitle(), is(title));
        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void createNewPage() throws Exception {
        service.createNewPage("Test Title", PermissionType.READ, new User());
        verify(pageDAO).savePage(Matchers.any(Page.class));
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
        page.setPermissionType(PermissionType.NO);
        when(pageDAO.getPage(anyInt())).thenReturn(page);

        Page actualPage = service.getPageToView(testUser, 0);
        assertThat(actualPage, is(nullValue()));

        actualPage = service.getPageToView(testAdmin, 0);
        assertThat(actualPage, is(page));

        page.setPermissionType(PermissionType.READ);
        actualPage = service.getPageToView(testUser, 0);
        assertThat(actualPage, is(page));
    }
}