package AIProject2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int k, n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter n: ");
        n = sc.nextInt();
        System.out.print("Please enter k: ");
        k = sc.nextInt();
        sc.close();

        int counterSum = 0, temp;
        Manager manager;
        for (int i = 0; i < k; i++) {
            manager = new Manager();
            temp = manager.solve(n);
            System.out.println("Fitness Function counter: " + temp);
            System.out.println("temperature: " + manager.temperature);
            System.out.println();
            counterSum += temp;

        }

        System.out.println("The Fitness Function counter is: " + (counterSum / k));
        System.out.println();

    }
}
