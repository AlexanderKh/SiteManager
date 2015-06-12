package alex.dao;

import alex.entity.Page;
import alex.entity.User;

import java.util.List;

public interface PageDAO {

    List<Page> getPagesByUser(int userID);

    List<Page> getPages();

    Page getPage(int id);

    void deletePage(Page page);

    void updatePage(Page page);

    void savePage(Page page);

    List<Page> getPagesNotVisibleForUser(User user);
}
