package alex.service;

import alex.config.AppConfig;
import alex.dao.*;
import alex.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class PermissionServiceImplTest {
    @Mock
    PageDAO pageDAO;
    @Mock
    UserDAO userDAO;
    @Mock
    PermissionDAO permissionDAO;
    @Autowired
    PermissionDAO actualPermissionDAO;
    @Autowired
    PermissionService service;

    @Before
    public void setUp() throws Exception {
        permissionDAO = mock(PermissionDAO.class);
        service.setPermissionDAO(permissionDAO);
    }

    @Test
    public void deletePermission() throws Exception {
        User user = new User("Test User", UserGroup.USER);
        Page page = new Page("Test Page", user);
        PermissionType type = PermissionType.EDIT;
        Permission permission = new Permission(user, page, type);

        service.deletePermission(permission);

        verify(permissionDAO).deletePermission(permission);
    }

    @Test
    public void addNewPermission() throws Exception{
        User user = new User("Test User", UserGroup.USER);
        Page page = new Page("Test Page", user);
        PermissionType type = PermissionType.EDIT;

        service.addNewPermission(user, page, type);

        verify(permissionDAO).savePermission(any(Permission.class));
    }

    @Test
    public void getPermissionsVisibleByUser() throws Exception{
        User firstUser = new User("First User", UserGroup.USER);
        User admin = new User("Admin", UserGroup.ADMIN);

        service.getPermissionsVisibleByUser(firstUser);
        verify(permissionDAO).getPermissionsByUser(firstUser);

        service.getPermissionsVisibleByUser(admin);
        verify(permissionDAO).getPermissionsAndUsers();
    }

    @Test
    public void changePermissionType() throws Exception {
        User user = new User("Test User", UserGroup.USER);
        Page page = new Page("Test Page", user);
        Permission permission = new Permission(user, page, PermissionType.READ);
        service.changePermissionType(permission, PermissionType.EDIT);

        verify(permissionDAO).updatePermission(permission);
    }

    @Test
    public void getUserPermissions() throws Exception {
        User firstUser = new User("First User", UserGroup.USER);
        User admin = new User("Admin", UserGroup.ADMIN);

        service.getUserPermissions(firstUser);
        verify(permissionDAO).getPermissionsByUser(firstUser);

        service.getUserPermissions(admin);
        verify(permissionDAO).getPermissionsByUser(admin);
    }

}