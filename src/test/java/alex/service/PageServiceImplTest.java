package alex.service;

import alex.entity.Page;
import alex.entity.User;
import alex.entity.UserGroup;
import org.junit.Test;
import org.mockito.Matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PageServiceImplTest extends AbstractServiceTest {
    @Test
    public void deletePage() throws Exception {
        Page testPage = new Page();

        pageService.deletePage(testPage);

        verify(pageDAO).deletePage(testPage);
    }

    @Test
    public void setPageContent() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");

        String content = "Test Content";
        pageService.setPageContent(testPage, content);

        assertThat(testPage.getContent(), is(content));
        verify(pageDAO).updatePage(testPage);
    }


    @Test
    public void getVisiblePages() throws Exception {
        User testUser = new User("Test User", UserGroup.USER);
        testUser.setId(10);
        User testAdmin = new User("Test Admin", UserGroup.ADMIN);

        pageService.getVisiblePages(testUser);
        verify(pageDAO).getPagesByUser(testUser.getId());

        pageService.getVisiblePages(testAdmin);
        verify(pageDAO).getPages();
    }

    @Test
    public void changePageName() throws Exception {
        Page testPage = new Page();
        testPage.setTitle("Test Title");

        String title = "Updated Title";
        pageService.changePageName(testPage, title);

        assertThat(testPage.getTitle(), is(title));
        verify(pageDAO).updatePage(testPage);
    }

    @Test
    public void createNewPage() throws Exception {
        pageService.createNewPage("Test Title");

        verify(pageDAO).savePage(Matchers.any(Page.class));
    }

    @Test
    public void getPages() throws Exception {
        pageService.getPages();

        verify(pageDAO).getPages();
    }

    @Test
    public void getPublicPages() throws Exception {
        pageService.getPublicPages();

        verify(pageDAO).getPublicPages();
    }

    @Test
    public void savePage() throws Exception {
        Page page = new Page("Test Page");

        pageService.savePage(page);

        verify(pageDAO).savePage(page);
    }

    @Test
    public void updatePage() throws Exception {
        Page page = new Page("Test Page");

        pageService.updatePage(page);

        verify(pageDAO).updatePage(page);
    }

    @Test
    public void getPageByID() throws Exception {
        int id = 0;

        pageService.getPageByID(id);

        verify(pageDAO).getPage(id);
    }

    @Test
    public void getPagesNotVisibleForUser() throws Exception {
        User user = new User("Test User", UserGroup.USER);

        pageService.getPagesWithoutUser(user);

        verify(pageDAO).getPagesWithoutUser(user);
    }
}