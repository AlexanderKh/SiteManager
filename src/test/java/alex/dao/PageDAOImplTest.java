package alex.dao;

import alex.config.AppConfig;
import alex.entity.*;
import org.hamcrest.Matchers;
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
        Page page = new Page("Page");
        assertThat(page.getId(), is(0));

        pageDAO.savePage(page);

        assertThat(page.getId(), not(0));

        Page actualPage = pageDAO.getPage(page.getId());
        assertThat(actualPage, is(page));

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
        List<Page> actualPages = pageDAO.getPagesNotVisibleForUser(user);

        assertThat(actualPages, Matchers.hasItem(page));
    }

    private void flush() {
        sessionFactory.getCurrentSession().flush();
    }
}