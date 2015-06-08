package alex.service;

import alex.dao.PageDAO;
import alex.dao.UserDAO;
import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private PageDAO pageDAO;
    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public User getUserByName(String input) {
        return userDAO.getUser(input);
    }

    public List<Page> getVisiblePages(User currentUser) {
        if (currentUser.getUserGroup() == UserGroup.ADMIN)
            return pageDAO.getPages();
            else
            return pageDAO.getPagesByAuthor(currentUser.getId());
    }

    public User register(String userName) {
        User user = new User();
        user.setName(userName);
        user.setUserGroup(UserGroup.USER);
        userDAO.saveUser(user);
        return user;
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

    public void deletePage(Page page) {
        pageDAO.deletePage(page);
    }

    public void changePermissionLevel(Page page, Permission permission) {
        page.setPermission(permission);
        pageDAO.updatePage(page);
    }

    public void setPageContent(Page page, String content) {
        page.setContent(content);
        userDAO.saveUser(page.getAuthor());
        pageDAO.updatePage(page);
    }

    public void changePageName(Page page, String input) {
        page.setTitle(input);
        pageDAO.updatePage(page);
    }

    public void createNewPage(String title, Permission permission, User currentUser) {
        Page page = new Page();
        page.setTitle(title);
        page.setPermission(permission);
        page.setAuthor(currentUser);
        pageDAO.addPage(page);
    }

    public Page getPageToView(User currentUser, int id) {
        Page page = pageDAO.getPage(id);
        if (page == null){
            return null;
        }
        if (page.getPermission() == Permission.NO &&
                currentUser.getUserGroup() == UserGroup.USER){
            page = null;
        }
        return page;
    }

    public List<User> getUsersWithTheirPages() {
        return userDAO.getUsersWithTheirPages();
    }

    public void deleteUser(User currentUser) {
        userDAO.deleteUser(currentUser);
    }

    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
