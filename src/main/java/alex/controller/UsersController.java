package alex.controller;

import alex.dao.UserDAO;
import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
import alex.service.PermissionService;
import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public String create(@RequestParam("name") String name,
                         @RequestParam("usergroup") String userGroup){
        User user = new User(name, UserGroup.valueOf(userGroup));
        userDAO.saveUser(user);
        return "added";
    }

    @RequestMapping("users/new")
    public String newUser(ModelMap model){
        return "adduser";
    }

    @RequestMapping("users/{id}")
    public String show(ModelMap modelMap,
                       @PathVariable("id") String id){
        int userId = Integer.valueOf(id);
        User user = userDAO.getUser(userId);
        List<Permission> permissionList = permissionService.getPermissionsVisibleByUser(user);
        modelMap.addAttribute("permissions", permissionList);
        return "usersAndPermissions";
    }
}
