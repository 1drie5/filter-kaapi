package u03_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ArrayList vs LinkedList: a rough timing comparison.
 * Exact numbers change on every computer. Look at which one is much slower.
 */
public class ArrayListVsLinkedListDemo {

    private static final int SIZE = 50_000;

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        // Adding at the front: ArrayList must shift every element, LinkedList does not.
        System.out.println("Add at the front, " + SIZE + " times:");
        System.out.println("  ArrayList:  " + addAtFront(arrayList) + " ms");
        System.out.println("  LinkedList: " + addAtFront(linkedList) + " ms");

        // Reading by index: ArrayList jumps straight there, LinkedList walks node by node.
        System.out.println("Read every element with get(i):");
        System.out.println("  ArrayList:  " + readByIndex(arrayList) + " ms");
        System.out.println("  LinkedList: " + readByIndex(linkedList) + " ms");
    }

    private static long addAtFront(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            list.add(0, i);
        }
        return System.currentTimeMillis() - start;
    }

    private static long readByIndex(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        return System.currentTimeMillis() - start;
    }
}
