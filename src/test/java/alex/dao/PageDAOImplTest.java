package alex.dao;

import alex.config.AppConfig;
import alex.entity.*;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
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


public class PageDAOImplTest extends AbstractDAOTest {


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

        assertThat(actualPages,not(hasItem(page)));
    }

}