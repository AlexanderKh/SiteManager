package alex.controller;

import alex.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PermissionsController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("permissions")
    public String index(ModelMap model){
        model.addAttribute("permissions", permissionService.getPermissions());
        return "permissions";
    }
}
