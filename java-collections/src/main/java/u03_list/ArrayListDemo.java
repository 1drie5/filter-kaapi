package u03_list;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList: a resizable array.
 *
 * - get(i) / set(i, x):             O(1)
 * - add at the end:                 amortized O(1)
 * - insert/remove at front/middle:  O(n)  (later elements shift)
 * - contains / indexOf:             O(n)
 *
 * Default choice when you need a list.
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        // The variable type is List (the interface); the object is an ArrayList.
        List<String> languages = new ArrayList<>();

        // Add
        languages.add("Java");
        languages.add("Python");
        languages.add("C++");
        languages.add(1, "Kotlin"); // insert at index 1; later elements shift right
        System.out.println("After adds:     " + languages);

        // Read and update
        System.out.println("get(2):         " + languages.get(2));
        languages.set(0, "Java 21");
        System.out.println("After set:      " + languages);

        // Search
        System.out.println("contains C++:   " + languages.contains("C++"));
        System.out.println("indexOf Python: " + languages.indexOf("Python"));
        System.out.println("indexOf Rust:   " + languages.indexOf("Rust")); // -1 means not found

        // Remove by index and remove by object
        languages.remove(1);     // by index  -> removes "Kotlin"
        languages.remove("C++"); // by object -> removes "C++"
        System.out.println("After removes:  " + languages);

        // PITFALL: on a List<Integer>, remove(int) and remove(Object) are different.
        List<Integer> numbers = new ArrayList<>(List.of(10, 20, 30, 40));
        numbers.remove(1);                   // remove(int index)   -> removes the value 20
        numbers.remove(Integer.valueOf(40)); // remove(Object o)    -> removes the value 40
        System.out.println("Integers:       " + numbers);

        // An ArrayList allows duplicates and null.
        List<String> messy = new ArrayList<>();
        messy.add("A");
        messy.add("A");
        messy.add(null);
        System.out.println("Duplicates and null: " + messy);

        // Iterate
        for (String language : languages) {
            System.out.println("  - " + language);
        }

        // Sort (null means natural ordering)
        List<Integer> unsorted = new ArrayList<>(List.of(5, 3, 8, 1));
        unsorted.sort(null);
        System.out.println("Sorted:         " + unsorted);

        // Under the hood, an ArrayList keeps its elements in an internal array.
        // When the array is full, the list creates a bigger one and copies the elements.
        // If you already know the size, new ArrayList<>(expectedSize) avoids repeated copying.
        List<Integer> big = new ArrayList<>(1000);
        System.out.println("Created with initial capacity 1000, size() = " + big.size()); // size is 0
    }
}
