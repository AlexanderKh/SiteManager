import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import service.SiteService;

import java.io.PrintStream;
import java.util.Scanner;

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

        }
    }

    private void login() {
        String input;
        out.print("Enter name to login as: ");
        input = in.next();
        service.getUserByName(input);
    }

    private int getIntFromUser(){
        String input = in.next();
        int result = 0;
        boolean correct = false;
        while (!correct){
            try {
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
