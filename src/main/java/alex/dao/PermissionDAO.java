package alex.dao;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;

import java.util.List;

public interface PermissionDAO {
    void savePermission(Permission permission);

    Permission getPermission(User user, Page page);

    void updatePermission(Permission permission);

    List<Permission> getPermissionsByUser(User user);

    List<Permission> getPermissionsByPage(Page page);

    List<Permission> getPermissions();

    void deletePermission(Permission permission);

    List<Permission> getPermissionsAndUsers();

    Permission getPermission(int id);

    void deleteByUser(User user);

    void deleteByPage(Page page);
}
