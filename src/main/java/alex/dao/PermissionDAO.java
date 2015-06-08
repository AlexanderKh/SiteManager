package alex.dao;

import alex.entity.Permission;

public interface PermissionDAO {
    void savePermission(Permission permission);

    Permission getPermission(int pageId, int userId);
}
