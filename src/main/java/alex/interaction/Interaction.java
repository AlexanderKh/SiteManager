package alex.interaction;

import alex.entity.Page;
import alex.entity.Permission;
import alex.entity.UserGroup;
import org.springframework.stereotype.Controller;
import alex.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import alex.service.SiteService;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@Controller
public class Interaction {
    private static final String INCORRECT_INPUT_MESSAGE = "Incorrect input, please try again: ";

    private User currentUser;

    private Scanner in;
    private PrintStream out;

    @Autowired
    private SiteService service;

    Interaction(){
        in = new Scanner(System.in);
        out = System.out;
    }

    public void menu(){
        int ans = 0;
        boolean exit = false;
        login();
        while (!exit){
            if (currentUser == null) {
                out.println("-----------------------");
                out.println("1 - LogIn");
                out.println("2 - Create new user");
                out.println("0 - Exit");
                out.println("-----------------------");
                ans = getIntFromUser();
                switch (ans) {
                    case 1:
                        login();
                        break;
                    case 2:
                        createNewUser();
                        break;
                    case 0:
                        exit = true;
                        break;
                }
            }

            if (currentUser.getUserGroup() == UserGroup.ADMIN){
                out.println("Current user: " + currentUser.getName() + " Group: Admin");
                out.println("-----------------------");
                out.println("1 - List all pages");
                out.println("2 - List all users w. their pages");
                out.println("3 - View page");
                out.println("4 - Create new page");
                out.println("5 - Edit page");
                out.println("9 - Delete profile");
                out.println("0 - LogOut");
                out.println("-----------------------");
                ans = getIntFromUser();
                switch (ans){
                    case 1:
                        listAllPages();
                        break;
                    case 2:
                        listAllUsers();
                        break;
                    case 3:
                        viewPage();
                        break;
                    case 4:
                        createNewPage();
                        break;
                    case 5:
                        editSpecificPage();
                        break;
                    case 9:
                        deleteProfile();
                    case 0:
                        currentUser = null;
                        break;
                }
            }

            if (currentUser.getUserGroup() == UserGroup.USER){
                out.println("Current user: " + currentUser.getName() + " Group: User");
                out.println("-----------------------");
                out.println("1 - List all pages");
                out.println("2 - View page");
                out.println("3 - Create new page");
                out.println("4 - Edit page");
                out.println("9 - Delete profile");
                out.println("0 - LogOut");
                out.println("-----------------------");
                ans = getIntFromUser();
                switch (ans){
                    case 1:
                        listAllPages();
                        break;
                    case 2:
                        viewPage();
                        break;
                    case 3:
                        createNewPage();
                        break;
                    case 4:
                        editSpecificPage();
                        break;
                    case 9:
                        deleteProfile();
                        break;
                    case 0:
                        currentUser = null;
                        break;
                }
            }
        }
    }

    private void editSpecificPage() {
        out.println("Enter page id: ");
        int input = getIntFromUser();
        Page page = service.getPageToEdit(currentUser, input);
        if (page == null){
            out.println("No such page or access denied");
        } else {
            editSpecificPage(page);
        }
    }

    private void editSpecificPage(Page page) {
        int ans = 0;
        boolean exit = false;
        login();
        while (!exit){
            if (currentUser == null) {
                out.println("Page id: " + page.getId() +
                        " Title: " + page.getTitle() +
                        " Owner: " + page.getAuthor().getName() +
                        " Permission level: " + page.getPermission());
                out.println("-----------------------");
                out.println("2 - Change title");
                out.println("3 - Change contents");
                out.println("4 - Change permission level");
                out.println("5 - Delete page");
                out.println("0 - End editing");
                out.println("-----------------------");
                ans = getIntFromUser();
                switch (ans) {
                    case 1:
                        out.println(page.getContent());
                        break;
                    case 2:
                        changeTitle(page);
                        break;
                    case 3:
                        changeContents(page);
                        break;
                    case 4:
                        changePermissionLevel(page);
                        break;
                    case 5:
                        deletePage(page);
                        break;
                    case 0:
                        exit = true;
                        break;
                }
            }
        }
    }

    private void deletePage(Page page) {
        out.print("Really? (y/n): ");
        String input = in.next();
        if (input.equals("y")){
            service.deletePage(page);
            out.println("Page deleted");
        }
    }

    private void changePermissionLevel(Page page) {
        out.println("Enter permission level, possible vars are: ");
        for (Permission permission : Permission.values()){
            out.println(permission.toString());
        }
        String permissionInput = in.next();
        Permission permission = Permission.valueOf(permissionInput);
        service.changePermissionLevel(page, permission);
    }

    private void changeContents(Page page) {
        out.println("Write new content in here: ");
        out.println();
        String content = "";
        do{
            content += in.next();
        } while (!content.equals(""));
        service.setPageContent(page, content);
    }

    private void changeTitle(Page page) {
        out.print("Enter new title: ");
        String input = in.next();
        service.changePageName(page, input);
    }

    private void createNewPage() {
        out.println("Enter page title: ");
        String title = in.next();
        out.println("Enter permission level, possible vars are: ");
        for (Permission permission : Permission.values()){
            out.println(permission.toString());
        }
        String permissionInput = in.next();
        Permission permission = Permission.valueOf(permissionInput);
        service.createNewPage(title, permission, currentUser);
    }

    private void viewPage() {
        out.println("Enter page id: ");
        int input = getIntFromUser();
        Page page = service.getPageToView(currentUser, input);
        if (page == null){
            out.println("No such page or access denied");
        } else {
            out.println(page.getContent());
        }
    }

    private void listAllUsers() {
        List<User> users = service.getUsersWithTheirPages();
        for (User user : users){
            out.println("User: " + user.getName() + " (id: " + user.getId() + ")");
            for (Page page : user.getPages()){
                out.printf("\t%4d\t%s\t%s\n", page.getId(), page.getTitle(), page.getPermission());
            }
        }
    }

    private void deleteProfile() {
        out.print("Really (y/n): ");
        String input = in.next();
        if (input.equals("y")){
            service.deleteUser(currentUser);
            currentUser = null;
            out.println("Okay, you are dead");
        }
    }

    private void listAllPages() {
        List<Page> pages = service.getPages(currentUser);
    }

    private void createNewUser() {
        out.print("Enter name of new user: ");
        currentUser = service.register(in.next());
    }

    private void login() {
        out.print("Enter name to login as: ");
        currentUser = service.getUserByName(in.next());
        if (currentUser == null)
            out.println("No such user");
    }

    public int getIntFromUser(){
        String input;
        int result = 0;
        boolean correct = false;
        while (!correct){
            try {
                input = in.next();
                result = Integer.valueOf(input);
                correct = true;
            } catch (NumberFormatException e){
                correct = false;
                out.println(INCORRECT_INPUT_MESSAGE);
            }
        }
        return result;
    }
}
