package alex.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("pages")
public class PagesController {
    @Autowired
    PageService pageService;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("pages", pageService.getPages());

        return "pages/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPage(ModelMap model){
        Page page = new Page();
        model.addAttribute("page", page);

        return "pages/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@ModelAttribute("page") Page page){
        pageService.savePage(page);

        return "redirect:/pages";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") String pageID,
                       ModelMap model){
        Page page = pageService.getPageByID(Integer.valueOf(pageID));
        List<Permission> permissions = permissionService.getPermissionsByUser(page);

        model.addAttribute("page", page);
        model.addAttribute("permissions", permissions);

        return "pages/show";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") String pageID,
                         @ModelAttribute("page") Page page,
                         ModelMap model){
        pageService.updatePage(page);

        model.addAttribute("page", page);

        return "redirect:/pages";
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.GET)
    public String newPermission(ModelMap model,
                                @PathVariable("id") String pageID){
        Page page = pageService.getPageByID(Integer.valueOf(pageID));
        List<User> users = userService.getUsersWithoutPage(page);

        model.addAttribute("page", page);
        model.addAttribute("users", users);
        model.addAttribute("types", PermissionType.values());

        return "pages/newPermission";
    }

    @RequestMapping(value = "/{id}/new", method = RequestMethod.POST)
    public String createPermission(@PathVariable("id") String pageID,
                                   @RequestParam("user") String userID,
                                   @RequestParam("type") PermissionType type){
        User user = userService.getUserByID(Integer.valueOf(userID));
        Page page = pageService.getPageByID(Integer.valueOf(pageID));

        permissionService.addNewPermission(user, page, type);

        return "redirect:/pages/" + pageID;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") String pageID) {
        Page page = pageService.getPageByID(Integer.valueOf(pageID));
        pageService.deletePage(page);

        return "redirect:/pages";
    }

    @RequestMapping(value = "/{pageID}/{permissionID}/delete", method = RequestMethod.POST)
    public String destroyPermission(@PathVariable("pageID") String pageID,
                                    @PathVariable("permissionID") String permissionID) {
        permissionService.deletePermissionByID(Integer.valueOf(permissionID));

        return "redirect:/pages/" + pageID;
    }
}
