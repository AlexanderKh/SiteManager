package alex.controller;

import alex.dao.UserDAO;
import alex.entity.User;
import alex.entity.UserGroup;
import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public void create(@RequestParam("name") String name,
                         @RequestParam("usergroup") String userGroup){
        User user = new User(name, UserGroup.valueOf(userGroup));
        userDAO.saveUser(user);
    }

    @RequestMapping("users/new")
    public String newUser(ModelMap model){
        return "adduser";
    }
}
