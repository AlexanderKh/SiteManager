package alex.dao;

import alex.entity.Page;
import alex.entity.Permission;

import java.util.List;

public interface PermissionDAO {
    void savePermission(Permission permission);

    Permission getPermission(int pageId, int userId);

    List<Page> getPagesVisibleForUser(int userId);
}
