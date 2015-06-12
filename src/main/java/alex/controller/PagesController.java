package alex.controller;

import alex.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {
    @Autowired
    PageService pageService;

    @RequestMapping("pages")
    public String index(ModelMap model){
        model.addAttribute("pages", pageService.getPages());
        return "pages";
    }
}
