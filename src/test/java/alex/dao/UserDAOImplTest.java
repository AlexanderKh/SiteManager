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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UserDAOImplTest extends AbstractDAOTest {

    @Test
    public void getUsersWithoutPage() throws Exception {
        List<User> actualUsers = userDAO.getUsersWithoutPage(page);

        assertThat(actualUsers, not(hasItem(user)));

    }

    @Test
    public void searchUsersByName() throws Exception {
        User vetal = new User("Vitaliy", UserGroup.USER);
        User viktor = new User("Viktor", UserGroup.USER);
        User pavel = new User("Pavel", UserGroup.USER);
        userDAO.saveUser(vetal);
        userDAO.saveUser(viktor);
        userDAO.saveUser(pavel);
        flush();
        evict(vetal);
        evict(viktor);
        evict(pavel);

        List<User> actualUsers = userDAO.searchUsersByName("Vi");

        assertThat(actualUsers, hasItem(vetal));
        assertThat(actualUsers, hasItem(viktor));
        assertThat(actualUsers, not(hasItem(pavel)));
    }

    @Test
    public void getUser() throws Exception {
        User actualUser = userDAO.getUser(user.getName());

        assertThat(actualUser, is(user));
    }

    @Test
    public void saveUser() throws Exception {
        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(user));
    }

    @Test
    public void deleteUser() throws Exception {
        permissionDAO.deletePermission(permission);

        userDAO.deleteUser(user);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, not(hasItem(user)));
    }

    @Test
    public void getUsers() throws Exception {
        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(user));
    }

    @Test
    public void updateUser() throws Exception{
        user.setName("Update");
        userDAO.updateUser(user);
        flush();
        evict(user);

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(user));
    }
}