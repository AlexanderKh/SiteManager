package alex.service;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;

import java.util.List;

public interface PageService {
    List<Page> getVisiblePages(User currentUser);

    Page getPageToView(User currentUser, int input);

    Page getPageToEdit(User currentUser, int id);

    void changePermissionLevel(Page page, Permission permission);

    void setPageContent(Page page, String content);

    void changePageName(Page page, String input);

    void createNewPage(String title, Permission permission, User currentUser);

    void deletePage(Page page);
}