package Queue;

public class CircularQueue {
    private int[] arr = new int[4];
    private int front = 0;
    private int rear = -1;
    private int size = 0;
    private int capacity = arr.length;

    public void enqueue(int data) {
        if (isFull()) { System.out.println("Queue is full"); return; }
        rear = (rear + 1) % capacity;
        arr[rear] = data;
        size++;
    }

    private boolean isFull()  { return size == capacity; }

    private boolean isEmpty() { return size == 0; }

    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        int data = arr[front];
        front = (front + 1) % capacity;
        size--;
        return data;
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return arr[front];
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}