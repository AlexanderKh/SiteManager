package alex.service;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.PermissionType;
import alex.entity.User;

import java.util.List;

public interface PageService {
    List<Page> getVisiblePages(User currentUser);

    Page getPage(int id);

    Page getPageToEdit(User currentUser, int id);

    void setPageContent(Page page, String content);

    void changePageName(Page page, String input);

    void createNewPage(String title);

    void deletePage(Page page);

    List<Page> getPagesWithoutUser(User user);

    List<Page> getPages();

    List<Permission> getPermissions(Page page);

    void deletePermission(int id);

    void savePage(Page page);
}
