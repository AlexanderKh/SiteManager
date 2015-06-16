package alex.service;

import alex.dao.PageDAO;
import alex.entity.Page;
import alex.entity.User;
import alex.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageDAO pageDAO;

    public void deletePage(Page page) {
        pageDAO.deletePage(page);
    }

    public List<Page> getPagesNotVisibleForUser(User user) {
        return pageDAO.getPagesNotVisibleForUser(user);
    }

    public List<Page> getPages() {
        return pageDAO.getPages();
    }

    public void setPageContent(Page page, String content) {
        page.setContent(content);
        pageDAO.updatePage(page);
    }

    public Page getPageToEdit(User currentUser, int id) {
        Page result = pageDAO.getPage(id);
        if (result == null)
            return null;
        if (currentUser.getUserGroup() == UserGroup.ADMIN)
            return result;

        return result;
    }

    public List<Page> getVisiblePages(User currentUser) {
        if (currentUser.getUserGroup() == UserGroup.ADMIN)
            return pageDAO.getPages();
        else
            return pageDAO.getPagesByUser(currentUser.getId());
    }

    public Page getPage(int id) {
        return pageDAO.getPage(id);
    }

    public void changePageName(Page page, String input) {
        page.setTitle(input);
        pageDAO.updatePage(page);
    }

    public void createNewPage(String title) {
        Page page = new Page();
        page.setTitle(title);
        pageDAO.savePage(page);
    }

    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }
}
