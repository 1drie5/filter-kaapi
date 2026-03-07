package Tree;

public class BSTMain {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(8);
        bst.insert(7);
        bst.insert(69);
        bst.insert(12);
        bst.insert(15);
        bst.insert(2);
        bst.insert(5);
        System.out.print("Inorder traversal : ");
        bst.inorder();
        System.out.print("Preorder traversal : ");
        bst.preorder();
        System.out.print("Postorder traversal : ");
        bst.postorder();
    }
}
