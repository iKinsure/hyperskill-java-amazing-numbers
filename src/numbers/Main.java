package numbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");

        while (true) {

            System.out.print("Enter a request: ");
            String[] input = scanner.nextLine().split("\\s");
            System.out.println();

            long a = Long.parseLong(input[0]);
            if (input.length == 1) {

                if (a == 0L) {
                    System.out.println("Goodbye!");
                    return;
                }
                if (a < 0L) {
                    System.out.println("The first parameter should be a natural number or zero");
                    continue;
                }

                System.out.println("Properties of " + a);
                Property.stream().forEachOrdered(property -> property.print(a));

            } else if (input.length == 2) {

                long b = Long.parseLong(input[1]);

                if (a < 0L) {
                    System.out.println("The first parameter should be a natural number or zero");
                    continue;
                }
                if (b <= 0L) {
                    System.out.println("The second parameter should be a natural number");
                    continue;
                }

                Property.print(a, b);

            } else if (input.length == 3) {

                long b = Long.parseLong(input[1]);
                String propertyName = input[2].toUpperCase();

                try {

                    Property property = Property.valueOf(propertyName);
                    Property.print(a, b, property);

                } catch (Exception e) {
                    System.out.printf("The property [%s] is wrong.%n", propertyName);
                    System.out.println("Available properties: " + Arrays.toString(Property.values()));
                }


            }


        }
    }
}
