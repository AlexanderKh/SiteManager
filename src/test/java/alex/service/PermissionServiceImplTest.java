package alex.service;

import alex.entity.*;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class PermissionServiceImplTest extends AbstractServiceTest {
    @Test
    public void deletePermission() throws Exception {
        User user = new User("Test User", UserGroup.USER);
        Page page = new Page("Test Page");
        PermissionType type = PermissionType.EDIT;
        Permission permission = new Permission(user, page, type);

        permissionService.deletePermission(permission);

        verify(permissionDAO).deletePermission(permission);
    }

    @Test
    public void addNewPermission() throws Exception {
        User user = new User("Test User", UserGroup.USER);
        Page page = new Page("Test Page");
        PermissionType type = PermissionType.EDIT;

        permissionService.addNewPermission(user, page, type);

        verify(permissionDAO).savePermission(any(Permission.class));
    }

    @Test
    public void getPermissionsByUser() throws Exception {
        User user = new User("Test User", UserGroup.USER);

        permissionService.getPermissionsByUser(user);

        verify(permissionDAO).getPermissionsByUser(user);
    }

    @Test
    public void getPermissionsByPage() throws Exception {
        Page page = new Page("Test Page");

        permissionService.getPermissionsByPage(page);

        verify(permissionDAO).getPermissionsByPage(page);
    }

    @Test
    public void savePermission() throws Exception {
        User user = new User("Test User", UserGroup.USER);
        Page page = new Page("Test Page");
        Permission permission = new Permission(user, page, PermissionType.READ);

        permissionService.savePermission(permission);

        verify(permissionDAO).savePermission(permission);
    }

    @Test
    public void changePermissionType() throws Exception {
        User user = new User("Test User", UserGroup.USER);
        Page page = new Page("Test Page");
        Permission permission = new Permission(user, page, PermissionType.READ);
        permissionService.changePermissionType(permission, PermissionType.EDIT);

        verify(permissionDAO).updatePermission(permission);
    }

    @Test
    public void getUserPermissions() throws Exception {
        User firstUser = new User("First User", UserGroup.USER);
        User admin = new User("Admin", UserGroup.ADMIN);

        permissionService.getUserPermissions(firstUser);
        verify(permissionDAO).getPermissionsByUser(firstUser);

        permissionService.getUserPermissions(admin);
        verify(permissionDAO).getPermissionsByUser(admin);
    }

}