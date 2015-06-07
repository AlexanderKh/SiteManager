package alex.interaction;

import alex.entity.Page;
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
                out.println("1 - Show all pages");
                out.println("2 - Show all users w. their pages");
                out.println("3 - View page");
                out.println("4 - Create new page");
                out.println("5 - Change page");
                out.println("9 - Delete profile");
                out.println("0 - LogOut");
                out.println("-----------------------");
                ans = getIntFromUser();
                switch (ans){
                    case 0:
                        currentUser = null;
                        break;
                }
            }
            if (currentUser.getUserGroup() == UserGroup.USER){
                out.println("Current user: " + currentUser.getName() + " Group: User");
                out.println("-----------------------");
                out.println("1 - Show all pages");
                out.println("2 - View page");
                out.println("3 - Create new page");
                out.println("4 - Change page");
                out.println("9 - Delete profile");
                out.println("0 - LogOut");
                out.println("-----------------------");
                ans = getIntFromUser();
                switch (ans){
                    case 1:
                        showAllPages();
                        break;
                    case 2:
                        viewPage();
                        break;
                    case 3:
                        createNewPage();
                        break;
                    case 4:
                        changePage();
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

    private void showAllPages() {
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
