package u03_list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * ListIterator: an Iterator for lists that can also go backwards
 * and can replace, insert and remove elements while iterating.
 *
 * The cursor sits BETWEEN elements:
 *
 *      A   B   C   D
 *    ^   ^   ^   ^   ^
 *
 * next() returns the element after the cursor and moves forward.
 * previous() returns the element before the cursor and moves back.
 */
public class ListIteratorDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D"));
        ListIterator<String> iterator = list.listIterator();

        // Forward
        while (iterator.hasNext()) {
            System.out.println("forward:  " + iterator.next());
        }

        // Backward (the cursor is at the end, so we can walk back)
        while (iterator.hasPrevious()) {
            System.out.println("backward: " + iterator.previous());
        }

        // Change the list while iterating (the cursor is back at the start)
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (value.equals("B")) {
                iterator.set("B2");   // replace B
            }
            if (value.equals("C")) {
                iterator.remove();    // remove C
            }
            if (value.equals("D")) {
                iterator.add("E");    // insert E right after D
            }
        }
        System.out.println("final list: " + list);
    }
}
