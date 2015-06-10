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
        if (result.getAuthor().getId() != currentUser.getId()){
            result = null;
        }

        return result;
    }

    public List<Page> getVisiblePages(User currentUser) {
        if (currentUser.getUserGroup() == UserGroup.ADMIN)
            return pageDAO.getPages();
        else
            return pageDAO.getPagesByAuthor(currentUser.getId());
    }

    public void changePageName(Page page, String input) {
        page.setTitle(input);
        pageDAO.updatePage(page);
    }

    public void createNewPage(String title, User currentUser) {
        Page page = new Page();
        page.setTitle(title);
        page.setAuthor(currentUser);
        pageDAO.savePage(page);
    }

    public Page getPageToView(User currentUser, int id) {
        Page page = pageDAO.getPage(id);
        if (page == null){
            return null;
        }

        return page;
    }


    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }
}
