import java.util.Arrays;
import java.util.List;

/** Demonstrates upper bounds and multiple bounds on type parameters. */
public class BoundedTypeParametersDemo {

    /** T must be Number or one of its subclasses. */
    static class NumericBox<T extends Number> {
        private final T value;

        NumericBox(T value) {
            this.value = value;
        }

        double asDouble() {
            return value.doubleValue();
        }
    }

    /**
     * T must be a Number AND implement Comparable<T>.
     * When multiple bounds are used, a class bound (if present) must come first.
     */
    static <T extends Number & Comparable<T>> T larger(T first, T second) {
        return first.compareTo(second) >= 0 ? first : second;
    }

    /** T must be comparable to a type that can compare with T. */
    static <T extends Comparable<? super T>> T max(List<T> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("values must not be empty");
        }

        T result = values.get(0);
        for (T value : values) {
            if (value.compareTo(result) > 0) {
                result = value;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NumericBox<Integer> integerBox = new NumericBox<>(69);
        NumericBox<Double> doubleBox = new NumericBox<>(69.69);
        System.out.println("Integer as double: " + integerBox.asDouble());
        System.out.println("Double as double: " + doubleBox.asDouble());

        System.out.println("Larger number: " + larger(69, 96));
        System.out.println("Maximum string: " + max(Arrays.asList("pear", "apple", "pineapple")));

        // Does not compile: String does not extend Number.
        // NumericBox<String> invalid = new NumericBox<>("hello");
    }
}
