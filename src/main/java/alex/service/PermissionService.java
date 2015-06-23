package alex.service;

import alex.dao.PermissionDAO;
import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.PermissionType;
import alex.entity.User;

import java.util.List;

public interface PermissionService {

    void setPermissionDAO(PermissionDAO permissionDAO);

    List<Permission> getPermissionsByUser(User user);

    List<Permission> getPermissionsByUser(Page page);

    List<Permission> getUserPermissions(User user);

    void deletePermission(Permission permission);

    void addNewPermission(User user, Page page, PermissionType type);

    void changePermissionType(Permission permission, PermissionType newType);

    void deletePermission(int id);

    void savePermission(Permission permission);
}
