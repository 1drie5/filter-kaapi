package u03_list;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Vector: like ArrayList, but older (Java 1.0) and synchronized.
 *
 * - Vector is synchronized, providing protection for individual operations.
 *   Compound operations and iteration still require care when shared between threads.
 * - Synchronization can add overhead.
 * - It is a legacy class. Prefer ArrayList when synchronization is not needed.
 */
public class VectorDemo {

    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        // The normal List methods work...
        vector.add("A");
        vector.add("B");
        System.out.println("get(0):       " + vector.get(0));

        // ...and Vector also has older methods that do the same jobs.
        vector.addElement("C");                                 // same as add("C")
        System.out.println("elementAt(1): " + vector.elementAt(1));   // same as get(1)
        System.out.println("lastElement:  " + vector.lastElement());

        // Enumeration is the old way to loop. Today we use for-each or Iterator.
        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println("  " + enumeration.nextElement());
        }
    }
}
