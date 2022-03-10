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
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n\n");

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

                Set<Property> containedProperties = new HashSet<>();
                Set<Property> ignoredProperties = new HashSet<>();
                Set<String> failedProperties = IntStream.range(2, input.length)
                        .mapToObj(i -> input[i].toUpperCase())
                        .map(s -> {
                            try {
                                if (s.startsWith("-")) {
                                    ignoredProperties.add(Property.valueOf(s.substring(1)));
                                } else {
                                    containedProperties.add(Property.valueOf(s));
                                }
                            } catch (Exception e) {
                                return s;
                            }
                            return null;
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());


                if (failedProperties.isEmpty()) {

                    var validation1 = Property.validate(containedProperties);
                    var validation2 = Property.validate(ignoredProperties);
                    boolean intersection = containedProperties.stream()
                            .anyMatch(ignoredProperties::contains);

                    if (validation1.isEmpty() && validation2.isEmpty() && !intersection) {
                        Property.print(a, b, containedProperties, ignoredProperties);
                    } else {
                        var str1 = validation1.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(", "));
                        var str2 = validation2.stream()
                                .map(set -> "[" + set.stream()
                                        .map(s -> "-" + s.name().toUpperCase())
                                        .collect(Collectors.joining(", ")) + "]")
                                .collect(Collectors.joining(", "));
                        System.out.println("The request contains mutually exclusive properties: " + str1 + str2);
                    }

                } else {
                    if (failedProperties.size() == 1) {
                        System.out.printf("The property [%s] is wrong.%n", failedProperties);

                    } else {
                        System.out.printf("The properties [%s] are wrong.%n", failedProperties);
                    }
                    System.out.println("Available properties: " + Arrays.toString(Property.values()));
                }
            }
        }
    }
}
