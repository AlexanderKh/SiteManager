package alex.dao;

import alex.entity.Permission;
import alex.entity.PermissionType;
import alex.entity.User;
import alex.entity.UserGroup;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class PermissionDAOImplTest extends AbstractDAOTest {

    @Test
    public void getPermission() throws Exception {
        Permission actualPermission = permissionDAO.getPermission(permission.getId());

        assertThat(actualPermission, is(permission));
    }

    @Test
    public void getPermissions() throws Exception {
        List<Permission> actualPermissions = permissionDAO.getPermissions();

        assertThat(actualPermissions, hasItem(permission));
    }

    @Test
    public void getPermissionsByUser() throws Exception {
        List<Permission> actualPermissions = permissionDAO.getPermissionsByUser(user);

        assertThat(actualPermissions.get(0).getUser(), is(user));
    }

    @Test
    public void getPermissionsByPage() throws Exception {
        List<Permission> actualPermissions = permissionDAO.getPermissionsByPage(page);

        assertThat(actualPermissions.get(0).getPage(), is(page));
    }

    @Test
    public void addPermission() throws Exception {
        Permission actualPermission = permissionDAO.getPermission(user, page);

        assertThat(actualPermission, is(permission));
    }

    @Test
    public void deletePermission() throws Exception {
        permissionDAO.deletePermission(permission);

        List<Permission> actualPermissions = permissionDAO.getPermissions();

        assertThat(actualPermissions, not(hasItem(permission)));
    }

    @Test
    public void updatePermission() throws Exception {
        Permission actualPermission = permissionDAO.getPermission(user, page);

        assertThat(actualPermission, is(permission));

        permission.setType(PermissionType.EDIT);

        permissionDAO.updatePermission(permission);
        flush();
        evict(permission);

        actualPermission = permissionDAO.getPermission(user, page);

        assertThat(actualPermission.getType(), is(PermissionType.EDIT));
    }

    @Test
    public void getPermissionsAndUsers() throws Exception {
        User pavel = new User("Pavel", UserGroup.USER);
        userDAO.saveUser(pavel);
        flush();
        evict(pavel);

        List<Permission> actualPermissions = permissionDAO.getPermissionsAndUsers();

        assertThat(actualPermissions, hasItem(permission));
    }
}