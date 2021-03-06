package alex.controller;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.service.PageService;
import alex.service.PermissionService;
import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("userPages")
public class UserPagesController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    UserService userService;
    @Autowired
    PageService pageService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        List<Permission> permissions = permissionService.getUserPermissions(user);
        List<Page> publicPages = pageService.getPublicPages();

        model.addAttribute("user", user);
        model.addAttribute("permissions", permissions);
        model.addAttribute("publicPages", publicPages);

        return "userPages/index";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(ModelMap modelMap,
                       @PathVariable("id") Integer pageID){
        Page page = pageService.getPageByID(pageID);

        modelMap.addAttribute("page", page);

        return "userPages/show";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(ModelMap modelMap,
                       @PathVariable("id") Integer pageID){
        Page page = pageService.getPageByID(pageID);

        modelMap.addAttribute("page", page);

        return "userPages/edit";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer pageID,
                         @RequestParam("content") String content,
                         ModelMap modelMap){
        Page page = pageService.getPageByID(pageID);
        pageService.setPageContent(page, content);
        return "redirect:/userPages";
    }
}