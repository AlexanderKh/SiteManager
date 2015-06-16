package alex.controller;

import alex.entity.Page;
import alex.entity.Permission;
import alex.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("pages")
public class PagesController {
    @Autowired
    PageService pageService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("pages", pageService.getPages());

        return "pages/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestParam("title") String title){
        pageService.createNewPage(title);

        return "redirect:/pages";
    }

    @RequestMapping("/new")
    public String newPage(ModelMap model){
        return "pages/new";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") String pageID,
                       ModelMap model){
        Page page = pageService.getPage(Integer.valueOf(pageID));
        List<Permission> permissions = pageService.getPermissions(page);

        model.addAttribute("page", page);
        model.addAttribute("permissions", permissions);

        return "pages/show";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") String pageID,
                         @RequestParam("content") String content,
                         ModelMap model){
        Page page = pageService.getPage(Integer.valueOf(pageID));
        pageService.setPageContent(page, content);

        model.addAttribute("page", page);

        return "redirect:/pages";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") String pageID) {
        Page page = pageService.getPage(Integer.valueOf(pageID));
        pageService.deletePage(page);

        return "redirect:/pages";
    }

    @RequestMapping(value = "/{pageID}/{permissionID}/delete", method = RequestMethod.POST)
    public String destroyPermission(@PathVariable("pageID") String pageID,
                                    @PathVariable("permissionID") String permissionID) {
        pageService.deletePermission(Integer.valueOf(permissionID));

        return "redirect:/pages/" + pageID;
    }
}
