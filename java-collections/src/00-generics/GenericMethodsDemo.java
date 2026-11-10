import java.util.Arrays;
import java.util.List;

/** Demonstrates generic methods, bounded generic methods, and type inference. */
public class GenericMethodsDemo {

    /** The method's type parameter is declared before its return type. */
    static <T> void swap(T[] array, int first, int second) {
        T temporary = array[first];
        array[first] = array[second];
        array[second] = temporary;
    }

    /** T must implement Comparable<T>, so values can be compared. */
    static <T extends Comparable<? super T>> T max(List<T> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("values must not be empty");
        }

        T best = values.get(0);
        for (T item : values) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    static <T> void printAll(T[] array) {
        for (T value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] words = {"one", "two", "three"};
        swap(words, 0, 2);
        printAll(words); // Type T is inferred as String.

        Integer[] numbers = {1, 2, 3, 4};
        swap(numbers, 1, 3);
        System.out.println(Arrays.toString(numbers));

        System.out.println("Maximum integer: " + max(Arrays.asList(4, 9, 2)));
        System.out.println("Maximum string: " + max(Arrays.asList("pear", "apple", "zebra")));

        // Explicit type witness. Usually, the compiler can infer T automatically.
        GenericMethodsDemo.<String>printAll(new String[]{"explicit", "type", "witness"});
    }
}
