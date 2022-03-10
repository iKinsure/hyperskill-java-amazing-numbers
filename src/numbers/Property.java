package numbers;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public enum Property {

    BUZZ(Algorithms::isBuzz),
    DUCK(Algorithms::isDuck),
    PALINDROMIC(Algorithms::isPalindromic),
    GAPFUL(Algorithms::isGapful),
    SPY(Algorithms::isSpy),
    SQUARE(Algorithms::isSquare),
    SUNNY(n -> Algorithms.isSquare(n + 1L)),
    JUMPING(Algorithms::isJumping),
    HAPPY(Algorithms::isHappy),
    SAD(n -> !Algorithms.isHappy(n)),
    EVEN(Algorithms::isEven),
    ODD(n -> !Algorithms.isEven(n));

    private final Checker checker;

    Property(Checker checker) {
        this.checker = checker;
    }

    public void print(long a) {
        System.out.println(this.name().toLowerCase() + ": " + checker.check(a));
    }

    public static void print(long a, long b) {
        String result = LongStream.range(a, a + b)
                .mapToObj(n -> n + " is " + Property.stream()
                        .filter(p -> p.checker.check(n))
                        .map(p -> p.name().toLowerCase())
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    public static void print(long a, long b, Set<Property> containedProperties, Set<Property> ignoredProperties) {
        long counter = 0L;
        while (counter < b) {

            long finalA = a;
            Set<Property> numberProperties = Property.stream()
                    .filter(p -> p.checker.check(finalA))
                    .collect(Collectors.toSet());

            boolean hasAllowedProperties = numberProperties.containsAll(containedProperties);
            boolean hasIgnoredProperty = ignoredProperties.stream().anyMatch(numberProperties::contains);

            if (hasAllowedProperties && !hasIgnoredProperty) {
                String propertiesStr = numberProperties.stream()
                        .map(property -> property.name().toLowerCase())
                        .collect(Collectors.joining(", "));
                System.out.println(a + " is " + propertiesStr);
                counter++;
            }

            a++;
        }
    }

    public static Stream<Property> stream() {
        return Arrays.stream(values());
    }

    /**
     * if properties violates exclusive, returns violated properties
     */
    public static Set<Set<Property>> validate(Set<Property> properties) {
        Set<Set<Property>> exclusiveProperties = Set.of(
                Set.of(EVEN, ODD),
                Set.of(DUCK, SPY),
                Set.of(SUNNY, SQUARE),
                Set.of(SAD, HAPPY)
        );
        return exclusiveProperties.stream()
                .filter(properties::containsAll)
                .collect(Collectors.toSet());
    }

}
