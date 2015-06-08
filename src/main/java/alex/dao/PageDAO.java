package alex.dao;

import alex.entity.Page;
import alex.entity.User;

import java.util.List;

public interface PageDAO {

    List<Page> getPagesByAuthor(int authorID);

    List<Page> getPages();

    Page getPage(int id);

    void deletePage(Page page);

    void updatePage(Page page);

    void addPage(Page page);
}
