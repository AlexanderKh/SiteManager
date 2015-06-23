package alex.service.impl;

import alex.dao.PageDAO;
import alex.dao.PermissionDAO;
import alex.entity.*;
import alex.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PageDAO pageDAO;
    @Autowired
    private PermissionDAO permissionDAO;

    public List<Permission> getPermissionsByUser(User user) {
        return permissionDAO.getPermissionsByUser(user);
    }

    public List<Permission> getPermissionsByUser(Page page) {
        return permissionDAO.getPermissionsByPage(page);
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

    public void savePermission(Permission permission) {
        permissionDAO.savePermission(permission);
    }

    public void deletePermissionByID(int id) {
        Permission permission = permissionDAO.getPermission(id);
        permissionDAO.deletePermission(permission);
    }

    public void setPermissionDAO(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

}
