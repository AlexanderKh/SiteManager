package alex.service;

import alex.dao.PermissionDAO;
import alex.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;

    public void setPermissionDAO(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    public List<Permission> getPermissionsVisibleByUser(User user) {
        if (user.getUserGroup() == UserGroup.ADMIN)
            return permissionDAO.getPermissionsAndUsers();
        else
            return permissionDAO.getPermissionsByUser(user);
    }

    public List<Permission> getUserPermissions(User user) {
        return permissionDAO.getPermissionsByUser(user);
    }

    public void deletePermission(Permission permission) {
        permissionDAO.deletePermission(permission);
    }

    public void addNewPermission(User user, Page page, PermissionType type) {
        Permission permission = new Permission(user, page, type);
        permissionDAO.savePermission(permission);
    }

    public void changePermissionType(Permission permission, PermissionType newType) {
        permission.setType(newType);
        permissionDAO.updatePermission(permission);
    }

    public List<Permission> getPermissions() {
        return permissionDAO.getPermissions();
    }

}
