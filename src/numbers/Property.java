package numbers;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public enum Property {

    BUZZ(n -> n % 10L == 7L || n % 7L == 0L),
    DUCK(n -> String.valueOf(n).contains("0")),
    PALINDROMIC(n -> new StringBuilder(String.valueOf(n)).reverse().toString().equals(String.valueOf(n))),
    GAPFUL((n) -> {
        String num = String.valueOf(n);
        return num.length() >= 3 &&
                n % Long.parseLong(num.charAt(0) + num.substring(num.length() - 1)) == 0;
    }),
    SPY(n -> {
        int resultSum = String.valueOf(n).chars().reduce(0, (sum, v) -> sum + (v - '0'));
        int resultProduct = String.valueOf(n).chars().reduce(1, (product, v) -> product * (v - '0'));
        return resultSum == resultProduct;
    }),
    EVEN(n -> n % 2L == 0L),
    ODD(n -> n % 2L != 0L);

    private final Checker checker;

    Property(Checker checker) {
        this.checker = checker;
    }

    public void print(long a) {
        System.out.println("    " + this.name().toLowerCase() + ": " + checker.check(a));
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

    public static void print(long a, long b, Property property) {
        String result = LongStream.iterate(a, i -> i + 1)
                .filter(n -> Property.stream()
                        .filter(p -> p.checker.check(n))
                        .anyMatch(p -> p.equals(property)))
                .limit(b)
                .mapToObj(n -> "        " + n + " is " + Property.stream()
                        .filter(p -> p.checker.check(n))
                        .map(p -> p.name().toLowerCase())
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    public static Stream<Property> stream() {
        return Arrays.stream(values());
    }

}
