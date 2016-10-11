package TropicalIsland;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Skaiol on 08.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Enter data as expected:");
        Scanner in = new Scanner(System.in);
        try {
            int islandCount = in.nextInt();
            ArrayList<TropicalIsland> islands = new ArrayList<TropicalIsland>();

            for (int k = 0; k < islandCount; k++) {
                int n = in.nextInt();
                int m = in.nextInt();

                int[][] array = new int[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        array[i][j] = in.nextInt();
                    }
                }

                islands.add(new TropicalIsland(array));
            }

            System.out.println("Results:");
            islands.forEach(x -> x.rain());
            islands.forEach(x -> System.out.println(x.getFullWaterVolume()));
        }
        finally {
            in.close();
        }
    }
}
