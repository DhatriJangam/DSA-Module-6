public class RBTreeNode {
    Task task;
    RBTreeNode left, right, parent;
    boolean color; // true for Red, false for Black

    public RBTreeNode(Task task) {
        this.task = task;
        this.color = true; // New nodes are initially red
    }
}

public class RBTree {
    private RBTreeNode root;

    // Methods for insertion, deletion, searching, and listing
    // Ensure the tree remains balanced after each operation
}
