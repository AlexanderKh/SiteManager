package alex.service;

import alex.entity.Page;
import alex.entity.PermissionType;
import alex.entity.User;

import java.util.List;

public interface PageService {
    List<Page> getVisiblePages(User currentUser);

    Page getPageToEdit(User currentUser, int id);

    void setPageContent(Page page, String content);

    void changePageName(Page page, String input);

    void createNewPage(String title);

    void deletePage(Page page);

    List<Page> getPagesNotVisibleForUser(User user);

    List<Page> getPages();
}
