package Queue;

public class QueueMain {
    public static void main(String[] args) {

        System.out.println("=== Linear Queue ===");
        Queue q = new Queue();
        q.enqueue(10);
        q.enqueue(23);
        q.enqueue(47);
        q.enqueue(69);
        q.show();
        System.out.println("Peek: " + q.peek());
        System.out.println("Dequeue: " + q.dequeue());
        q.show();
        q.enqueue(78);   // fills the freed slot (linear queue can't reuse it)
        q.show();

        System.out.println("\n=== Circular Queue ===");
        CircularQueue cq = new CircularQueue();
        cq.enqueue(10);
        cq.enqueue(23);
        cq.enqueue(47);
        cq.enqueue(69);
        cq.show();
        System.out.println("Peek: " + cq.peek());
        System.out.println("Dequeue: " + cq.dequeue());
        cq.show();
        cq.enqueue(78);  // circular queue CAN reuse the freed slot
        cq.show();
    }
}