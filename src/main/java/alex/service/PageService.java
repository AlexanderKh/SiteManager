package alex.service;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.PermissionType;
import alex.entity.User;

import java.util.List;

public interface PageService {
    List<Page> getPages();

    List<Page> getVisiblePages(User currentUser);

    Page getPageByID(int id);

    void setPageContent(Page page, String content);

    void changePageName(Page page, String title);

    void createNewPage(String title);

    void deletePage(Page page);

    List<Page> getPagesWithoutUser(User user);

    void deletePermission(int id);

    void savePage(Page page);

    void updatePage(Page page);

    List<Page> getPublicPages();
}
