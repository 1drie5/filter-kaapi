package u04_queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Queue: holds elements waiting to be processed.
 * FIFO (First In, First Out) is a common ordering.
 *
 *   Insert at the TAIL  ->  [ A, B, C ]  ->  Remove from the HEAD
 *
 * Queue extends Collection. Its three core operations have two versions:
 *
 *                   Throws an exception     Returns a special value
 *   Insert          add(e)                  offer(e)
 *   Remove          remove()                poll()
 *   Examine head    element()               peek()
 *
 * When an operation cannot be done (the queue is empty, or full if it has
 * a size limit), the left column throws an exception. The right column returns
 * false (insert) or null (remove/examine).
 *
 * LinkedList implements Queue and follows FIFO, so we use it here.
 */
public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        // Insert at the tail
        queue.add("Iddu");
        queue.offer("Grace");
        queue.offer("Rabia");
        System.out.println("Queue:       " + queue);            // [Iddu, Grace, Rabia]

        // Look at the head without removing it
        System.out.println("peek():      " + queue.peek());     // Iddu
        System.out.println("element():   " + queue.element());  // Iddu

        // Remove from the head: the first one in is the first one out
        System.out.println("poll():      " + queue.poll());     // Iddu
        System.out.println("remove():    " + queue.remove());   // Grace
        System.out.println("Queue now:   " + queue);            // [Rabia]
        System.out.println("size():      " + queue.size());     // 1
        System.out.println("isEmpty():   " + queue.isEmpty());  // false

        // On an EMPTY queue the two versions behave differently
        queue.clear();
        System.out.println("poll():      " + queue.poll());     // null
        System.out.println("peek():      " + queue.peek());     // null
        try {
            queue.remove();                                     // remove() throws on an empty queue; element() does too
        } catch (NoSuchElementException e) {
            System.out.println("remove():    NoSuchElementException");
        }
    }
}
