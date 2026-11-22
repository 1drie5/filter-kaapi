package u02_iterable_and_iterator;

import java.util.Iterator;

public class OurGenericListDemo {

    public static void main(String[] args) {
        OurGenericList<Integer> list = new OurGenericList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // Approach 1: use the Iterator directly.
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Approach 2: use the enhanced for-loop.
        // Java obtains an Iterator behind the scenes.
        for (int number : list) {
            System.out.println(number);
        }
    }
}
