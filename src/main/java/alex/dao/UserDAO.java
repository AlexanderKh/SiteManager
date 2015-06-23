package alex.dao;

import alex.entity.Page;
import alex.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserDAO {

    User getUser(String input);

    void saveUser(User user);

    void deleteUser(User currentUser);

    List<User> getUsers();

    void updateUser(User user);

    User getUserByID(int id);

    List<User> getUsersWithoutPage(Page page);

    List<User> searchUsersByName(String name);
}
