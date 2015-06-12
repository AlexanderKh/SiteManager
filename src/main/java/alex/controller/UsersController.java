package alex.controller;

import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "users")
    public String print(ModelMap model){
        model.addAttribute("users", userService.getUsers());
        return "users";
    }
}
