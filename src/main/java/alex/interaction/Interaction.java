package alex.interaction;

import alex.entity.Page;
import alex.entity.User;
import alex.entity.UserGroup;
import alex.service.PageService;
import alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@Controller
public class Interaction {

    private User currentUser;

    private Scanner in;
    private PrintStream out;

    @Autowired
    private PageService pageService;
    @Autowired
    private UserService userService;
    @Autowired
    private InteractionHelper helper;
    @Autowired
    private PageEditInteraction pageEditInteraction;
    @Autowired
    private PermissionInteraction permissionInteraction;

    Interaction(){
        in = IOContainer.in;
        out = IOContainer.out;
    }

    public void menu(){
        int ans;
        boolean exit = false;
        while (!exit){
            if (currentUser == null) {
                out.println("-----------------------");
                out.println("1 - LogIn");
                out.println("2 - Create new user");
                out.println("3 - Permission Editor");
                out.println("0 - Exit");
                out.println("-----------------------");
                ans = helper.getIntFromUser();
                switch (ans) {
                    case 1:
                        login();
                        break;
                    case 2:
                        createNewUser();
                        break;
                    case 3:
                        permissionInteraction.interact();
                    case 0:
                        exit = true;
                        break;
                }
                continue;
            }
            out.println("-----------------------");
            out.println("Current user: " + currentUser.getName() +
                    "\tGroup: " + currentUser.getUserGroup());
            out.println("-----------------------");
            out.println("1 - List all pages");
            out.println("2 - Create new page");
            out.println("3 - Edit page");
            out.println("9 - Delete profile");
            out.println("0 - LogOut");
            out.println("-----------------------");
            ans = helper.getIntFromUser();
            switch (ans){
                case 1:
                    listAllPages();
                    break;
                case 2:
                    createNewPage();
                    break;
                case 3:
                    editSpecificPage();
                    break;
                case 9:
                    deleteProfile();
                case 0:
                    currentUser = null;
                    break;
            }
        }
    }

    private void editSpecificPage() {
        out.println("Enter page id: ");
        int input = helper.getIntFromUser();
        Page page = pageService.getPageToEdit(currentUser, input);
        if (page == null){
            out.println("No such page or access denied");
        } else {
            pageEditInteraction.setPage(page);
            pageEditInteraction.edit();
        }
    }

    private void createNewPage() {
        out.println("Enter page title: ");
        String title = in.next() + in.nextLine();
        pageService.createNewPage(title);
    }

    private void deleteProfile() {
        out.print("Really (y/n): ");
        String input = in.next();
        if (input.equals("y")){
            userService.deleteUser(currentUser);
            currentUser = null;
        }
    }

    private void listAllPages() {
        List<Page> pages = pageService.getVisiblePages(currentUser);
        for (Page page : pages){
            out.printf("Id: %4d\tTitle: %s\n", page.getId(), page.getTitle());
        }
    }

    private void createNewUser() {
        out.print("Enter name of new user: ");
        currentUser = userService.register(in.next());
    }

    private void login() {
        out.print("Enter name to login as: ");
        currentUser = userService.getUserByName(in.next());
        if (currentUser == null)
            out.println("No such user");
    }

}
