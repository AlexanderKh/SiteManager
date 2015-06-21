package alex.controller;

import alex.dao.PageDAO;
import alex.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    PageDAO pageDAO;

    @RequestMapping("/")
    public String publicIindex(ModelMap model){
        List<Page> publicPages = pageDAO.getPublicPages();
        model.addAttribute("pages", publicPages);
        return "public";
    }

    @RequestMapping("{id}")
    public String show(ModelMap model,
                       @PathVariable("id") Integer pageID){
        Page page = pageDAO.getPage(pageID);
        model.addAttribute("page", page);
        return "publicPage";
    }

    @RequestMapping("/admin")
    public String adminIndex(){
        return "BasicTemplate";
    }
}
