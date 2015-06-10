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

import java.util.List;

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

    @Test
    public void deletePage() throws Exception {
        Page testPage = new Page();

        service.deletePage(testPage);

        verify(pageDAO).deletePage(testPage);
    }

    @Test
    public void setPageContent() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");

        String content = "Test Content";
        service.setPageContent(testPage, content);

        assertThat(testPage.getContent(), is(content));
        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void getPageToEdit() throws Exception {
        User testUser = new User("Test User", UserGroup.USER);

        service.getPageToEdit(testUser, 0);
        verify(pageDAO).getPage(0);
    }


    @Test
    public void getVisiblePages() throws Exception {
        User testUser = new User("Test User", UserGroup.USER);
        testUser.setId(10);
        User testAdmin = new User("Test Admin", UserGroup.ADMIN);

        service.getVisiblePages(testUser);
        verify(pageDAO).getPagesByAuthor(testUser.getId());

        service.getVisiblePages(testAdmin);
        verify(pageDAO).getPages();
    }

    @Test
    public void changePageName() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");

        String title = "Updated Title";
        service.changePageName(testPage, title);

        assertThat(testPage.getTitle(), is(title));
        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void createNewPage() throws Exception {
        service.createNewPage("Test Title");

        verify(pageDAO).savePage(Matchers.any(Page.class));
    }

    @Test
    public void getPagesNotVisibleForUser() throws Exception {
        User user = new User("Test User", UserGroup.USER);

        service.getPagesNotVisibleForUser(user);

        verify(pageDAO).getPagesNotVisibleForUser(user);
    }
}