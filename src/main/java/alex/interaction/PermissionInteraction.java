package alex.interaction;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.User;
import alex.service.PageService;
import alex.service.PermissionService;
import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class PermissionInteraction {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private InteractionHelper helper;
    private Scanner in;
    private PrintStream out;

    PermissionInteraction(){
        in = IOContainer.in;
        out = IOContainer.out;
    }

    public void interact(){
        int ans = 0;
        boolean exit = false;
        while (!exit){
            out.println("-----------------------");
            out.println("1 - Show Visible Pages for User");
            out.println("2 - Show all Users with Pages");
            out.println("3 - Add permission");
            out.println("4 - Remove permission");
            out.println("5 - Change permission");
            out.println("0 - End editing");
            out.println("-----------------------");
            ans = helper.getIntFromUser();
            switch (ans) {
                case 1:
                    showVisiblePagesForUser();
                    break;
                case 2:
                    showAllUsersWithPages();
                    break;
                case 3:
                    addPermission();
                    break;
                case 4:
                    removePermission();
                    break;
                case 5:
                    changePermission();
                    break;
                case 0:
                    exit = true;
                    break;
            }

        }
    }

    private void showAllUsersWithPages() {
        out.println("Select perspective");
        User user = selectUser();
        out.println("All user and pages visible by chosen user");
        List<Permission> permissions = permissionService.getPermissionsVisibleByUser(user);
    }

    private void showVisiblePagesForUser() {
        User user = selectUser();
        out.println("Visible pages for user: " + user.getName());
        List<Permission> permissions = permissionService.getUserPermissions(user);
        for (Permission permission : permissions){
            Page page = permission.getPage();
            out.printf("%4d\t%20s\t%5s\n", page.getId(), page.getTitle(), permission.getType());
        }
    }

    private User selectUser() {
        List<User> allUsers = userService.getUsers();
        out.println("All users list: ");
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            out.printf("%3d\t%s\t%s\n", i, user.getName(), user.getUserGroup());
        }
        out.print("Enter number of user to select: ");
        int userNO = helper.getIntFromUser();
        return allUsers.get(userNO);
    }

}
