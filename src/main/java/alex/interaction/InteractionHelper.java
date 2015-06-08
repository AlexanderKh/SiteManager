package alex.interaction;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import sun.misc.Contended;

import java.io.PrintStream;
import java.util.Scanner;

@Controller
public class InteractionHelper {
    private static final String INCORRECT_INPUT_MESSAGE = "Incorrect input, please try again: ";

    private Scanner in;
    private PrintStream out;

    InteractionHelper(){
        in = IOContainer.in;
        out = IOContainer.out;
    }

    public int getIntFromUser(){
        String input;
        int result = 0;
        boolean correct = false;
        while (!correct){
            try {
                input = in.next() + in.nextLine();
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
