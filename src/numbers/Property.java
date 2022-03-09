package numbers;

public class Property {

    private final String name;
    private final Checker checker;

    public Property(String name, Checker checker) {
        this.name = name;
        this.checker = checker;
    }

    public void print(long n) {
        System.out.println("    " + name + ": " + checker.check(n));
    }

    public String getName() {
        return name;
    }

    public Checker getChecker() {
        return checker;
    }
}
