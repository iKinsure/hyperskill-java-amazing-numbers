package numbers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                "- two natural numbers and two properties to search for;\n" +
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

            } else {

                long b = Long.parseLong(input[1]);

                List<String> failedList = new ArrayList<>();
                List<Property> properties = IntStream.range(2, input.length)
                        .mapToObj(i -> input[i].toUpperCase())
                        .map(s -> {
                            try {
                                return Property.valueOf(s);
                            } catch (Exception e) {
                                failedList.add(s);
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());


                if (failedList.isEmpty()) {

                    var validation = Property.validate(properties);
                    if (validation.isEmpty()) {
                        Property.print(a, b, properties);
                    } else {
                        System.out.println("The request contains mutually exclusive properties: " + validation);
                    }

                } else {
                    if (failedList.size() == 1) {
                        System.out.printf("The property [%s] is wrong.%n", failedList);

                    } else {
                        System.out.printf("The properties [%s] are wrong.%n", failedList);
                    }
                    System.out.println("Available properties: " + Arrays.toString(Property.values()));
                }
            }
        }
    }
}
