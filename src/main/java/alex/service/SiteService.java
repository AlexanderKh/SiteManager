package alex.service;

import alex.entity.Page;
import alex.entity.User;

import java.util.List;

public interface SiteService {
    List<User> getUsers();

    User getUserByName(String input);

    List<Page> getPages(User currentUser);

    User register(String userName);
}
