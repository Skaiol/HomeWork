package InfiniteSequence;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Skaiol on 08.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter data as expected. Enter empty line for exit:");
        try (Scanner in = new Scanner(System.in)) {
            ArrayList<String> subSequences = new ArrayList<>();
            while (true) {
                String input = in.nextLine();
                if (input.equals("")) {
                    break;
                }
                subSequences.add(input);
            }

            System.out.println("Results:");
            InfiniteSequence sequence = new InfiniteSequence();
            for (String subSequence : subSequences) {
                BigInteger position = sequence.getPositionOfSubSequence(subSequence);
                System.out.println(position);
            }
        }
    }
}
