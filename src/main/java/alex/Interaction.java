package alex;

import org.springframework.stereotype.Controller;
import alex.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import alex.service.SiteService;

import java.io.PrintStream;
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
            out.println("-----------------------");
            out.println("Current user: " + currentUser);
            out.println("1 - List all available pages");
            out.println("2 - List all users and their pages");
            out.println("3 - Go to page control");
            ans = getIntFromUser();
        }
    }

    private void login() {
        String input;
        out.print("Enter name to login as: ");
        input = in.next();
        out.println(service.getUsers().toString());
    }

    private int getIntFromUser(){
        String input = in.next();
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
