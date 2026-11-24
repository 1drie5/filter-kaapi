package u03_list;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * LinkedList: a doubly linked list that implements both List and Deque.
 *
 * - add/remove at either end:       O(1)
 * - get(i):                         O(n)  (walks the nodes from the nearer end)
 * - add(i, x) / remove(i):          O(n)  (the walk to index i is the cost)
 *
 * In practice ArrayList is faster for most work, and ArrayDeque is the better
 * queue/stack. LinkedList is still worth knowing because it appears in
 * interviews and in older code.
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("B");
        list.addFirst("A");
        list.addLast("C");
        System.out.println("List:              " + list);
        System.out.println("First / Last:      " + list.getFirst() + " / " + list.getLast());

        list.add(1, "X"); // List method: works, but must walk to index 1
        System.out.println("After add(1, X):   " + list);

        list.removeFirst();
        list.removeLast();
        System.out.println("After removing ends: " + list);

        // Used as a queue (FIFO): add at the tail, remove from the head.
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println("queue.peek():      " + queue.peek()); // 1, looks without removing
        System.out.println("queue.poll():      " + queue.poll()); // 1, removes the head
        System.out.println("Queue now:         " + queue);

        // Used as a stack (LIFO): push and pop both work at the head.
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack now:         " + stack);        // [3, 2, 1]
        System.out.println("stack.pop():       " + stack.pop());  // 3

        // Empty-list behaviour: poll() and peek() return null,
        // while removeFirst() and pop() throw an exception.
        LinkedList<String> empty = new LinkedList<>();
        System.out.println("empty.poll():      " + empty.poll());
        System.out.println("empty.peek():      " + empty.peek());
        try {
            empty.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("empty.removeFirst() threw NoSuchElementException");
        }

        // LinkedList allows null elements. ArrayDeque does not, because poll() and
        // peek() use null to signal an empty queue—storing null would make them ambiguous.
        empty.add(null);
        System.out.println("After adding null: " + empty);
    }
}
