package numbers;

import java.util.stream.IntStream;

public final class Algorithms {

    public static boolean isHappy(long n) {
        while (true) {
            n = String.valueOf(n).chars()
                    .map(i -> i - '0')
                    .map(i -> i * i)
                    .reduce(0, Integer::sum);
            if (n == 1L) {
                return true;
            }
            if (n == 4L) {
                return false;
            }
        }
    }

    public static boolean isEven(long n) {
        return n % 2L == 0L;
    }

    public static boolean isSquare(long n) {
        double square = Math.sqrt(n);
        return square - Math.floor(square) == 0;
    }

    public static boolean isSpy(long n) {
        String nStr = String.valueOf(n);
        int resultSum = nStr.chars().reduce(0, (sum, v) -> sum + (v - '0'));
        int resultProduct = nStr.chars().reduce(1, (product, v) -> product * (v - '0'));
        return resultSum == resultProduct;
    }

    public static boolean isJumping(long n) {
        String nStr = String.valueOf(n);
        return IntStream.range(1, nStr.length())
                .allMatch(i -> Math.abs(nStr.charAt(i - 1) - nStr.charAt(i)) == 1);
    }

    public static boolean isGapful(long n) {
        String nStr = String.valueOf(n);
        return nStr.length() >= 3 &&
                n % Long.parseLong(nStr.charAt(0) + nStr.substring(nStr.length() - 1)) == 0;
    }

    public static boolean isPalindromic(long n) {
        String nStr = String.valueOf(n);
        return new StringBuilder(nStr).reverse().toString().equals(nStr);
    }

    public static boolean isDuck(long n) {
        return String.valueOf(n).contains("0");
    }

    public static boolean isBuzz(long n) {
        return n % 10L == 7L || n % 7L == 0L;
    }
}
