package u02_iterable_and_iterator;

import java.util.Iterator;
import java.util.List;

public class BasicIterableDemo {

    public static void main(String[] args) {
        // List implements Collection, and Collection extends Iterable.
        Iterable<String> names = List.of("Iddu", "Grace", "Rabia");

        // Traverse using an Iterator.
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Traverse the same Iterable using an enhanced for-loop.
        for (String name : names) {
            System.out.println(name);
        }
    }
}
