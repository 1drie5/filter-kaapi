package u02_iterable_and_iterator;

import java.util.Iterator;

public class IndependentIteratorsDemo {

    public static void main(String[] args) {
        OurGenericList<String> list = new OurGenericList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator<String> first = list.iterator();
        Iterator<String> second = list.iterator();

        System.out.println(first.next());  // A
        System.out.println(first.next());  // B

        // The second iterator has its own position, so it starts at A.
        System.out.println(second.next()); // A
    }
}
