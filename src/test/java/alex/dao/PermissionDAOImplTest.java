package alex.dao;

import alex.config.AppConfig;
import alex.entity.*;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class PermissionDAOImplTest extends AbstractDAOTest {

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
}