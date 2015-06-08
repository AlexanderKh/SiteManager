package alex.service;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;

import java.util.List;

public interface SiteService {
    List<User> getUsers();

    User getUserByName(String input);

    List<Page> getVisiblePages(User currentUser);

    User register(String userName);

    Page getPageToEdit(User currentUser, int id);

    void deletePage(Page page);

    void changePermissionLevel(Page page, Permission permission);

    void setPageContent(Page page, String content);

    void changePageName(Page page, String input);

    void createNewPage(String title, Permission permission, User currentUser);

    Page getPageToView(User currentUser, int input);

    List<User> getUsersWithTheirPages();

    void deleteUser(User currentUser);

}
