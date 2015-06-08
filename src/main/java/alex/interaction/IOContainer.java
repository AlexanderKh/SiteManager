package alex.interaction;

import java.io.PrintStream;
import java.util.Scanner;

public class IOContainer {
    public static Scanner in;
    public static PrintStream out;

    static {
        in = new Scanner(System.in);
        out = System.out;
    }

}
