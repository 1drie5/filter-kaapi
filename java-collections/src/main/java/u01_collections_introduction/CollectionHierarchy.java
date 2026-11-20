package u01_collections_introduction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class CollectionHierarchy {

    // Works with any modifiable Collection<Integer> thanks to polymorphism
    static void describe(String label, Collection<Integer> c) {
        c.add(3);
        c.add(1);
        c.add(3);
        c.add(2);
        System.out.println(label + " -> " + c + " (size " + c.size() + ")");
    }

    public static void main(String[] args) {
        describe("ArrayList ", new ArrayList<>());   // keeps order, keeps duplicates
        describe("LinkedList", new LinkedList<>());  // same contract, different internals
        describe("HashSet   ", new HashSet<>());     // no duplicates, no guaranteed order (small Integers happen to print sorted)
        describe("TreeSet   ", new TreeSet<>());     // no duplicates, sorted
        describe("ArrayDeque", new ArrayDeque<>());  // queue/deque, keeps duplicates

        // Programming to the interface
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        System.out.println(list.getClass().getSimpleName() + ", "
                + set.getClass().getSimpleName() + ", "
                + queue.getClass().getSimpleName());

        // Everything in the Collection hierarchy is Iterable
        Iterable<Integer> it = new ArrayList<>(List.of(10, 20));
        for (int x : it) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
