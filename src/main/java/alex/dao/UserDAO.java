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

    User getUser(int id);

    List<User> getUsersWithoutPage(Page page);
}
