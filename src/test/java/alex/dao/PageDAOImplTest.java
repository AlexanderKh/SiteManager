package alex.dao;

import alex.config.AppConfig;
import alex.entity.*;
import org.hibernate.SessionFactory;
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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
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
    @Autowired
    PermissionDAO permissionDAO;
    @Autowired
    SessionFactory sessionFactory;
    Page page;
    User user;

    @Before
    public void setUp() throws Exception {
        user = new User("Test User", UserGroup.ADMIN);
        userDAO.saveUser(user);
        evict(user);

        page = new Page("Test Title");
        page.setContent("Test Content");
        pageDAO.savePage(page);
        evict(page);
    }

    private void evict(Object o) {
        sessionFactory.getCurrentSession().evict(o);
    }

    @Test
    public void getPages() throws Exception {
        List<Page> actualPages = pageDAO.getPages();

        assertThat(actualPages, hasItem(page));
    }

    @Test
    public void getPage() throws Exception {
        Page actualPage = pageDAO.getPage(page.getId());

        assertThat(actualPage, is(page));
    }

    @Test
    public void deletePage() throws Exception {
        pageDAO.deletePage(page);
        List<Page> actualPages = pageDAO.getPages();

        assertThat(actualPages, not(hasItem(page)));
    }

    @Test
    public void updatePage() throws Exception {
        page.setTitle("Update Page");
        pageDAO.updatePage(page);

        Page actualPage = pageDAO.getPage(page.getId());

        assertThat(actualPage, is(page));
    }

    @Test
    public void savePage() throws Exception {
        List<Page> actualPages = pageDAO.getPages();

        assertThat(actualPages, hasItem(page));
    }

    @Test
    public void getPagesNotVisibleForUser() throws Exception {
        Permission permission = new Permission(user, page, PermissionType.READ);
        permissionDAO.savePermission(permission);
        Page hiddenPage = new Page("Test Hidden Page");
        pageDAO.savePage(hiddenPage);
        Permission hiddenPermission = new Permission(user, hiddenPage, PermissionType.NO);
        permissionDAO.savePermission(hiddenPermission);

        List<Page> actualPages = pageDAO.getPagesNotVisibleForUser(user);

        assertThat(actualPages, not(hasItem(page)));
        assertThat(actualPages, hasItem(hiddenPage));
    }
}