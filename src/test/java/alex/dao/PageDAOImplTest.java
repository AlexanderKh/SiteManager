package alex.dao;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class PageDAOImplTest {

    @Autowired
    PageDAO pageDAO;
    Page testPage;

    @Before
    public void setUp() throws Exception {
        Page page = new Page();
        page.setAuthor(new User());
        page.setContent("Test Content");
        page.setTitle("Test Title");
        page.setPermission(Permission.READ);
        testPage = page;
    }

    @After
    public void tearDown() throws Exception {
        pageDAO.deletePage(testPage);
    }

    @Test
    public void getPages() throws Exception {
        pageDAO.addPage(testPage);
        List<Page> actualPages = pageDAO.getPages();

        assertThat(actualPages, contains(testPage));
    }

    @Test
    public void getPagesByAuthor() throws Exception {

    }

    @Test
    public void getPage() throws Exception {

    }

    @Test
    public void deletePage() throws Exception {

    }

    @Test
    public void updatePage() throws Exception {

    }

    @Test
    public void addPage() throws Exception {

    }
}