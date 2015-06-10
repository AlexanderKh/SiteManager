package alex.service;

import alex.entity.User;
import alex.entity.UserGroup;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest extends AbstractServiceTest {
    @Test
    public void getUsers() throws Exception {
        List<User> testList = new ArrayList<User>();
        when(userDAO.getUsers()).thenReturn(testList);

        List<User> actualUsers = userService.getUsers();

        assertThat(actualUsers, is(testList));
    }

    @Test
    public void getUserByName() throws Exception {
        User testUser = new User();
        String testUserName = "Test";
        when(userDAO.getUser(testUserName)).thenReturn(testUser);

        User actualUser = userService.getUserByName(testUserName);

        assertThat(actualUser, is(testUser));
    }

    @Test
    public void register() throws Exception {
        User actualUser = userService.register("Test");

        assertThat(actualUser.getName(), is("Test"));
        assertThat(actualUser.getUserGroup(), is(UserGroup.USER));
    }

    @Test
    public void deleteUser() throws Exception {
        User testUser = new User();

        userService.deleteUser(testUser);

        verify(userDAO).deleteUser(testUser);
    }
}