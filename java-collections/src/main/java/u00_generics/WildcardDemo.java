package u00_generics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates unbounded, upper-bounded, and lower-bounded wildcards.
 * Also demonstrates PECS: Producer Extends, Consumer Super.  
 */
public class WildcardDemo {

    /** Upper-bounded wildcard: read Number values from the producer. */
    static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number number : numbers) {
            total += number.doubleValue();
        }
        return total;
    }

    /** Lower-bounded wildcard: add Integers to a consumer of Integer or a supertype. */
    static void addIntegers(List<? super Integer> destination) {
        for (int number = -3; number <= 3; number++) {
            destination.add(number);
        }
    }

    /** Unbounded wildcard: process elements without depending on their specific type. */
    static void printAny(List<?> values) {
        for (Object value : values) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /** Copies values from a producer to a consumer. */
    static <T> void copy(List<? super T> destination, List<? extends T> source) {
        for (T value : source) {
            destination.add(value);
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 5, 8);
        List<Double> decimals = Arrays.asList(2.6, 4.3);
        System.out.println("Sum of integers: " + sum(integers));
        System.out.println("Sum of decimals: " + sum(decimals));

        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        addIntegers(numbers);
        addIntegers(objects);
        System.out.println("Numbers: " + numbers);
        System.out.println("Objects: " + objects);

        List<Number> copiedNumbers = new ArrayList<>();
        copy(copiedNumbers, integers); // Integer is a Number.
        System.out.println("Copied numbers: " + copiedNumbers);

        printAny(Arrays.asList("a", "b"));
        printAny(integers);

        // A List<? extends Number> is safe to read as Number, but you cannot
        // add an Integer because the actual list might be List<Double>.
        // List<? extends Number> producer = integers;
        // producer.add(4); // Compile-time error.
    }
}
