package LinkedList;

class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }
}
public class LinkedList {
    Node head = null;
    public void add(int data){
        Node newNode = new Node(data);

        Node current = head;
        if (head == null){
            head = newNode;
        } else {
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
    }
    public void addFirst(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    public void delete(int data) {
        if (head == null) return; // Bug 2: NPE guard

        // Handle head deletion
        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }
    public void printValues(){
        Node current = head;
        while (current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}
