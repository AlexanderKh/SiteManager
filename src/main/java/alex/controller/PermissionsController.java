package alex.controller;

import alex.dao.PageDAO;
import alex.dao.PermissionDAO;
import alex.dao.UserDAO;
import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.PermissionType;
import alex.entity.User;
import alex.service.PageService;
import alex.service.PermissionService;
import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PermissionsController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    PageService pageService;
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    PageDAO pageDAO;
    @Autowired
    PermissionDAO permissionDAO;

    @RequestMapping(name = "permissions", method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("permissions", permissionService.getPermissions());
        return "permissions";
    }

    @RequestMapping(value = "permissions", method = RequestMethod.POST)
    public String create(@RequestParam("user") String userID,
                         @RequestParam("page") String pageID,
                         @RequestParam("type") String typeNAME,
                         ModelMap model){
        User user = userDAO.getUser(Integer.valueOf(userID));
        Page page = pageDAO.getPage(Integer.valueOf(pageID));
        PermissionType type = PermissionType.valueOf(typeNAME);
        Permission existingPermission = permissionDAO.getPermission(user, page);
        if (existingPermission == null)
            permissionService.addNewPermission(user, page, type);
        else
            model.addAttribute("message", "This permission already exists, choose other user or page");
        return index(model);
    }

    @RequestMapping("permissions/new")
    public String newPermission(ModelMap modelMap){
        modelMap.addAttribute("permissionTypes", PermissionType.values());
        modelMap.addAttribute("pages", pageService.getPages());
        modelMap.addAttribute("users", userService.getUsers());
        return "addPermission";
    }

    @RequestMapping(value = "permissions/{id}", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") String permissionID){
        int id = Integer.valueOf(permissionID);
        permissionService.deletePermission(id);
        return "redirect:/permissions";
    }
}
