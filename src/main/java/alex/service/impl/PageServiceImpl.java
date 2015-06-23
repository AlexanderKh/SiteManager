package alex.service.impl;

import alex.dao.PageDAO;
import alex.dao.PermissionDAO;
import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
import alex.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageDAO pageDAO;
    @Autowired
    private PermissionDAO permissionDAO;

    public List<Page> getPages() {
        return pageDAO.getPages();
    }

    public List<Page> getPublicPages() {
        return pageDAO.getPublicPages();
    }

    public void deletePage(Page page) {
        permissionDAO.deleteByPage(page);
        pageDAO.deletePage(page);
    }

    public List<Page> getPagesWithoutUser(User user) {
        return pageDAO.getPagesWithoutUser(user);
    }

    public void savePage(Page page) {
        pageDAO.savePage(page);
    }

    public void updatePage(Page page) {
        pageDAO.updatePage(page);
    }

    public void setPageContent(Page page, String content) {
        page.setContent(content);
        pageDAO.updatePage(page);
    }


    public List<Page> getVisiblePages(User currentUser) {
        if (currentUser.getUserGroup() == UserGroup.ADMIN)
            return pageDAO.getPages();
        else
            return pageDAO.getPagesByUser(currentUser.getId());
    }

    public Page getPageByID(int id) {
        return pageDAO.getPage(id);
    }

    public void changePageName(Page page, String title) {
        page.setTitle(title);
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
