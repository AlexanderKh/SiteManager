package alex.dao;

import alex.entity.*;
import org.hamcrest.Matchers;
import org.junit.Test;

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
    public void getPagesByUser() throws Exception {
        List<Page> actualPages = pageDAO.getPagesByUser(user.getId());

        assertThat(actualPages, hasItem(page));
    }

    @Test
    public void getPublicPages() throws Exception {
        List<Page> actualPages = pageDAO.getPublicPages();

        assertThat(actualPages, not(hasItem(page)));

        page.setPublicPage(true);
        pageDAO.updatePage(page);
        flush();

        actualPages = pageDAO.getPublicPages();

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
        List<Page> actualPages = pageDAO.getPagesWithoutUser(user);

        assertThat(actualPages,not(hasItem(page)));
    }

}