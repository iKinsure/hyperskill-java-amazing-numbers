package numbers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Property {

    private final String name;
    private final Checker checker;

    private Property(String name, Checker checker) {
        this.name = name;
        this.checker = checker;
    }

    public static Property of(String name, Checker checker) {
        return new Property(name, checker);
    }

    public void print(long a) {
        System.out.println("    " + name + ": " + checker.check(a));
    }

    public static void print(long a, long b, List<Property> properties) {
        String result = LongStream.range(a, a + b)
                .mapToObj(n -> n + " is " + properties.stream()
                        .filter(property -> property.checker.check(n))
                        .map(property -> property.name)
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

}
