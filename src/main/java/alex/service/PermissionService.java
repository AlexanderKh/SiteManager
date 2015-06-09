package alex.service;

import alex.dao.PermissionDAO;
import alex.entity.Permission;
import alex.entity.User;

import java.util.List;

public interface PermissionService {

    void setPermissionDAO(PermissionDAO permissionDAO);

    List<Permission> getPermissionsVisibleByUser(User user);

    List<Permission> getUserPermissions(User user);
}
