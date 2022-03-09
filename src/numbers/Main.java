package numbers;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Property> properties = List.of(
                new Property("buzz", n -> n % 10L == 7L || n % 7L == 0L),
                new Property("duck", n -> String.valueOf(n).contains("0")),
                new Property("palindromic", n -> new StringBuilder(
                        String.valueOf(n)).reverse().toString().equals(String.valueOf(n))),
                new Property("gapful", (n) -> {
                    String num = String.valueOf(n);
                    if (num.length() < 3) {
                        return false;
                    }
                    return n % Long.parseLong(num.charAt(0) + num.substring(num.length() - 1)) == 0L;
                }),
                new Property("even", n -> n % 2L == 0L),
                new Property("odd", n -> n % 2L != 0L)
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

            if (input.length == 1) {

                long a = Long.parseLong(input[0]);

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

                long a = Long.parseLong(input[0]);
                long b = Long.parseLong(input[1]);

                if (a < 0L) {
                    System.out.println("The first parameter should be a natural number or zero");
                    continue;
                }
                if (b <= 0L) {
                    System.out.println("The second parameter should be a natural number");
                    continue;
                }

                LongStream.range(a, a + b).forEach(n -> {
                    String result = properties.stream()
                            .filter(property -> property.getChecker().check(n))
                            .map(Property::getName)
                            .collect(Collectors.joining(", "));
                    System.out.println(n + " is " + result);

                });

            }


        }
    }
}
