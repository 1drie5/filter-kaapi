package Stack;

public class Stack {
    private int[] arr = new int[5];
    private int top;
    private int size;
    public Stack() {
        size = arr.length;
        top = -1;
    }
    public void push(int data) {
        if (top < size - 1) arr[++top] = data;
        else System.out.println("Stack Overflow");
    }
    /* prints uninitialized zeros
    public void printStack(){
        for(int n : arr) System.out.print(n + " ");
        System.out.println();
    }*/
    public void printStack() {
        for (int i = 0; i <= top; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
    public int pop() {
        if (top == -1) { System.out.println("Stack Underflow"); return -1; }
        return arr[top--];
    }
    public int peek() {
        if (top == -1) { System.out.println("Stack Underflow"); return -1; }
        return arr[top];
    }
}
