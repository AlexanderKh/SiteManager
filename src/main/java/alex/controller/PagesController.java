package alex.controller;

import alex.dao.PageDAO;
import alex.dao.PermissionDAO;
import alex.entity.Page;
import alex.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {
    @Autowired
    PageService pageService;
    @Autowired
    PageDAO pageDAO;
    @Autowired
    PermissionDAO permissionDAO;

    @RequestMapping(value = "pages", method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("pages", pageService.getPages());
        return "pages";
    }

    @RequestMapping("pages/new")
    public String newPage(ModelMap model){
        return "addPage";
    }

    @RequestMapping(value = "pages", method = RequestMethod.POST)
    public String create(@RequestParam("title") String title){
        pageService.createNewPage(title);
        return "redirect:pages";
    }

    @RequestMapping(value = "pages/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") String pageID,
                       ModelMap model){
        int id = Integer.valueOf(pageID);
        Page page = pageDAO.getPage(id);
        model.addAttribute("page", page);
        return "pageEdit";
    }

    @RequestMapping(value = "pages/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") String pageID,
                         @RequestParam("content") String content,
                         ModelMap model){
        int id = Integer.valueOf(pageID);
        Page page = pageDAO.getPage(id);
        pageService.setPageContent(page, content);
        model.addAttribute("page", page);
        return "redirect:";
    }

    @RequestMapping(value = "pages/{id}/delete", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") String pageID) {
        int id = Integer.valueOf(pageID);
        Page page = pageDAO.getPage(id);
        permissionDAO.deleteByPage(page);
        pageDAO.deletePage(page);

        return "redirect:/pages";
    }
}
