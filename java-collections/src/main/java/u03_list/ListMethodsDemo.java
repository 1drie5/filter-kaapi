package u03_list;

import java.util.ArrayList;
import java.util.List;

/**
 * Common List methods. They work the same on ArrayList, LinkedList and Vector.
 */
public class ListMethodsDemo {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        // Add
        fruits.add("Apple");
        fruits.add("Cherry");
        fruits.add(1, "Banana");   // insert at index 1
        System.out.println("After adding:  " + fruits);

        // Read
        System.out.println("get(0):        " + fruits.get(0));
        System.out.println("size():        " + fruits.size());
        System.out.println("contains:      " + fruits.contains("Cherry"));
        System.out.println("indexOf:       " + fruits.indexOf("Cherry"));

        // Update and remove
        fruits.set(0, "Apricot");  // replace the element at index 0
        fruits.remove(1);          // remove by index  -> removes "Banana"
        fruits.remove("Cherry");   // remove by object -> removes "Cherry"
        System.out.println("After changes: " + fruits);

        // Sort and filter
        List<Integer> numbers = new ArrayList<>(List.of(30, 10, 20));
        numbers.sort(null);                      // null means natural (ascending) order
        System.out.println("Sorted:        " + numbers);
        numbers.removeIf(n -> n < 20);           // remove every number below 20
        System.out.println("After removeIf: " + numbers);

        // List.of creates a list that cannot be changed.
        List<String> fixed = List.of("a", "b");
        try {
            fixed.add("c");
        } catch (UnsupportedOperationException e) {
            System.out.println("List.of cannot be changed");
        }
    }
}
