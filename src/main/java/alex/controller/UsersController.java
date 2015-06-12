package alex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {


    @RequestMapping(value = "users")
    public String print(ModelMap model){
        model.addAttribute("hi", "hi");
        return "users";
    }
}
