package numbers;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Property> properties = List.of(
                Property.of("buzz", n -> n % 10L == 7L || n % 7L == 0L),
                Property.of("duck", n -> String.valueOf(n).contains("0")),
                Property.of("palindromic", n -> new StringBuilder(
                        String.valueOf(n)).reverse().toString().equals(String.valueOf(n))),
                Property.of("gapful", (n) -> {
                    String num = String.valueOf(n);
                    return num.length() >= 3 &&
                            n % Long.parseLong(num.charAt(0) + num.substring(num.length() - 1)) == 0;
                }),
                Property.of("even", n -> n % 2L == 0L),
                Property.of("odd", n -> n % 2L != 0L)
        );

        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
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
                properties.forEach(property -> property.print(a));


            } else {

                long b = Long.parseLong(input[1]);

                if (a < 0L) {
                    System.out.println("The first parameter should be a natural number or zero");
                    continue;
                }
                if (b <= 0L) {
                    System.out.println("The second parameter should be a natural number");
                    continue;
                }

                Property.print(a, b, properties);
            }


        }
    }
}
