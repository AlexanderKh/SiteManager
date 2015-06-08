package alex.dao;

import alex.config.AppConfig;
import alex.entity.Page;
import alex.entity.PermissionType;
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

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PageDAOImplTest {
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
        page.setPermissionType(PermissionType.READ);

        User user = new User();
        user.setName("Test User");
        user.setUserGroup(UserGroup.ADMIN);
        userDAO.saveUser(user);

        testUser = user;
        testPage = page;
    }

    @After
    public void tearDown() throws Exception {
        pageDAO.deletePage(testPage);
        userDAO.deleteUser(testUser);
    }

    @Test
    public void getPages() throws Exception {
        pageDAO.savePage(testPage);
        pageDAO.updatePage(testPage);
        List<Page> actualPages = pageDAO.getPages();

        assertThat(actualPages, hasItem(testPage));
    }

    @Test
    public void getPagesByAuthor() throws Exception {
        pageDAO.savePage(testPage);
        List<Page> actualUserPages = pageDAO.getPagesByAuthor(testUser.getId());

        assertThat(actualUserPages, hasItem(testPage));
    }

    @Test
    public void getPage() throws Exception {
        pageDAO.savePage(testPage);
        Page actualPage = pageDAO.getPage(testPage.getId());

        assertThat(actualPage, is(testPage));
    }

    @Test
    public void deletePage() throws Exception {
        pageDAO.savePage(testPage);
        pageDAO.deletePage(testPage);
        List<Page> actualPages = pageDAO.getPages();

        assertThat(actualPages, not(hasItem(testPage)));
    }

    @Test
    public void updatePage() throws Exception {
        pageDAO.savePage(testPage);
        testPage.setTitle("Update Page");
        pageDAO.updatePage(testPage);

        Page actualPage = pageDAO.getPage(testPage.getId());

        assertThat(actualPage, is(testPage));
    }

    @Test
    public void savePage() throws Exception {
        pageDAO.savePage(testPage);

        List<Page> actualPages = pageDAO.getPages();

        assertThat(actualPages, hasItem(testPage));
    }
}