package numbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public enum Property {

    BUZZ(n -> n % 10L == 7L || n % 7L == 0L),
    DUCK(n -> String.valueOf(n).contains("0")),
    PALINDROMIC(n -> new StringBuilder(String.valueOf(n)).reverse().toString().equals(String.valueOf(n))),
    GAPFUL((n) -> {
        String nStr = String.valueOf(n);
        return nStr.length() >= 3 &&
                n % Long.parseLong(nStr.charAt(0) + nStr.substring(nStr.length() - 1)) == 0;
    }),
    SPY(n -> {
        String nStr = String.valueOf(n);
        int resultSum = nStr.chars().reduce(0, (sum, v) -> sum + (v - '0'));
        int resultProduct = nStr.chars().reduce(1, (product, v) -> product * (v - '0'));
        return resultSum == resultProduct;
    }),
    SQUARE(n -> {
        double square = Math.sqrt(n);
        return square - Math.floor(square) == 0;
    }),
    SUNNY(n -> {
        double square = Math.sqrt(n + 1L);
        return square - Math.floor(square) == 0;
    }),
    JUMPING(n -> {
        String nStr = String.valueOf(n);
        for (int i = 1; i < nStr.length(); i++) {
            int firstDigit = nStr.charAt(i - 1) - '0';
            int nextDigit = nStr.charAt(i) - '0';
            if (Math.abs(firstDigit - nextDigit) != 1) {
                return false;
            }
        }
        return true;
    }),
    EVEN(n -> n % 2L == 0L),
    ODD(n -> n % 2L != 0L);

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

    public static void print(long a, long b, List<Property> properties) {
        String result = LongStream.iterate(a, i -> i + 1)
                .filter(n -> Property.stream()
                        .filter(p -> p.checker.check(n))
                        .collect(Collectors.toList())
                        .containsAll(properties))
                .limit(b)
                .mapToObj(n -> n + " is " + Property.stream()
                        .filter(p -> p.checker.check(n))
                        .map(p -> p.name().toLowerCase())
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    public static Stream<Property> stream() {
        return Arrays.stream(values());
    }

    /**
     * if properties violates exclusive, returns violated properties
     */
    public static List<List<Property>> validate(List<Property> properties) {
        List<List<Property>> exclusiveProperties = List.of(
                List.of(EVEN, ODD),
                List.of(DUCK, SPY),
                List.of(SUNNY, SQUARE)
        );
        return exclusiveProperties.stream()
                .filter(properties::containsAll)
                .collect(Collectors.toList());
    }

}
