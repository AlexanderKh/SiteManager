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

        List<User> actualUsers = userDAO.getUsers();

        assertThat(actualUsers, hasItem(user));
    }
}