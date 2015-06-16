package alex.controller;

import alex.entity.Permission;
import alex.entity.User;
import alex.entity.UserGroup;
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
@RequestMapping("users")
public class UsersController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("users", userService.getUsers());

        return "/users/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("userGroup") String userGroup){
        userService.createUser(name, UserGroup.valueOf(userGroup));

        return "redirect:/users";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(ModelMap model){
        model.addAttribute("userGroups", UserGroup.values());

        return "/users/new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap,
                       @PathVariable("id") String userID){
        User user = userService.getUser(Integer.valueOf(userID));
        List<Permission> permissionList = userService.getPermissions(user);
        modelMap.addAttribute("permissions", permissionList);
        modelMap.addAttribute("user", user);

        return "/users/show";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String createPermission(ModelMap modelMap,
                                   @PathVariable("id") String userID,
                                   @RequestParam("user") User user){
        modelMap.addAttribute("user", user);

        return "/users/show";
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.GET)
    public String newPermission(ModelMap model,
                                @PathVariable("id") String userID){
        User user = userService.getUser(Integer.valueOf(userID));
        model.addAttribute("user", user);

        return "/users/newPermission";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") String userID) {
        User user = userService.getUser(Integer.valueOf(userID));
        userService.deleteUser(user);

        return "redirect:/users";
    }

    @RequestMapping(value = "/{userID}/{permissionID}/delete", method = RequestMethod.POST)
    public String destroyPermission(@PathVariable("userID") String userID,
                                    @PathVariable("permissionID") String permissionID) {
        userService.deletePermission(Integer.valueOf(permissionID));

        return "redirect:/users/" + userID;
    }
}
