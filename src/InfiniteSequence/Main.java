package InfiniteSequence;

import java.util.Scanner;

/**
 * Created by Skaiol on 08.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter data as expected:");
        Scanner in = new Scanner(System.in);
        try {
            new InfiniteSequence().run();
        }
        finally {
            in.close();
        }
    }
}
