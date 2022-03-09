package numbers;

import java.util.Scanner;

public class Main {

    public final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("Enter a natural number:");

        int a = main.scanner.nextInt();
        if (a <= 0) {
            System.out.println("This number is not natural!");
            return;
        }

        boolean isEven = a % 2 == 0;
        System.out.println("This number is " + (isEven ? "Even" : "Odd") + ".");

        boolean endsWith7 = a % 10 == 7;
        boolean isDivisibleBy7 = a % 7 == 0;

        if (endsWith7 && isDivisibleBy7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d is divisible by 7 and ends with 7.%n", a);
        } else if (isDivisibleBy7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d is divisible by 7.%n", a);
        } else if (endsWith7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d ends with 7.%n", a);
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d is neither divisible by 7 nor does it end with 7.%n", a);
        }

    }
}
