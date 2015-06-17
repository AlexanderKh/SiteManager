package alex.controller;

import alex.entity.*;
import alex.service.PageService;
import alex.service.PermissionService;
import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    PageService pageService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("users", userService.getUsers());

        return "users/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(ModelMap model){
        User user = new User();

        model.addAttribute("user", user);
        model.addAttribute("userGroups", UserGroup.values());

        return "users/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);

        return "redirect:/users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap,
                       @PathVariable("id") String userID){
        User user = userService.getUser(Integer.valueOf(userID));
        List<Permission> permissionList = userService.getPermissions(user);
        modelMap.addAttribute("permissions", permissionList);
        modelMap.addAttribute("user", user);

        return "users/show";
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.GET)
    public String newPermission(ModelMap model,
                                @PathVariable("id") String userID){
        User user = userService.getUser(Integer.valueOf(userID));
        List<Page> pages = pageService.getPagesWithoutUser(user);

        model.addAttribute("user", user);
        model.addAttribute("pages", pages);
        model.addAttribute("types", PermissionType.values());

        return "users/newPermission";
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.POST)
    public String createPermission(@PathVariable("id") String userID,
                                   @RequestParam("page") String pageID,
                                   @RequestParam("type") PermissionType type){
        User user = userService.getUser(Integer.valueOf(userID));
        Page page = pageService.getPage(Integer.valueOf(pageID));

        permissionService.addNewPermission(user, page, type);

        return "redirect:/users/" + userID;
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
        permissionService.deletePermission(Integer.valueOf(permissionID));

        return "redirect:/users/" + userID;
    }
}
