package LinkedList;

public class LinkedListMain {
    public static void main(String[] args) {
        LinkedList nums = new LinkedList();
        nums.add(69);
        nums.add(54);
        nums.add(32);
        nums.add(75);
        nums.addFirst(7);
        nums.printValues();
        nums.delete(7);
        nums.printValues();
        nums.delete(32);
        nums.printValues();
    }
}
