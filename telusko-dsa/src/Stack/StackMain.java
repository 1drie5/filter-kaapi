package Stack;

public class StackMain {
    public static void main(String[] args) {
        Stack nums = new Stack();
        nums.push(23);
        nums.push(69);
        System.out.println(nums.pop());
        nums.push(47);
        nums.push(86);
        System.out.println(nums.peek());
        nums.push(96);
        nums.printStack();
    }
}
