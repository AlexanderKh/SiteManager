package alex.service;

import alex.dao.PermissionDAO;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
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
            return permissionDAO.getPermissions();
        else
            return permissionDAO.getPermissionsByUser(user);
    }

    public List<Permission> getUserPermissions(User user) {
        return permissionDAO.getPermissionsByUser(user);
    }
}
