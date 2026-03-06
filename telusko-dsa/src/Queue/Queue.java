package Queue;

public class Queue {
    private int front = 0;
    private int rear = -1;
    private int size = 0;
    private int[] arr = new int[4];

    public void enqueue(int data) {
        if (!isFull()) {
            rear++;
            arr[rear] = data;
            size++;
        } else {
            System.out.println("Queue is full");
        }
    }

    private boolean isFull() { return rear == arr.length - 1; }

    private boolean isEmpty() { return size == 0; }

    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        int data = arr[front];
        front++;
        size--;
        return data;
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return arr[front];
    }

    public void show() {
        for (int i = front; i < front + size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}