import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    private AVLTreeNode root;

    // Get the height of the node
    private int height(AVLTreeNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Right rotate the subtree rooted with y
    private AVLTreeNode rightRotate(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate the subtree rooted with x
    private AVLTreeNode leftRotate(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of node N
    private int getBalance(AVLTreeNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a movie into the AVL tree
    public void insert(Movie movie) {
        root = insert(root, movie);
    }

    private AVLTreeNode insert(AVLTreeNode node, Movie movie) {
        // 1. Perform the normal BST insertion
        if (node == null)
            return (new AVLTreeNode(movie));

        if (movie.getTitle().compareTo(node.movie.getTitle()) < 0)
            node.left = insert(node.left, movie);
        else if (movie.getTitle().compareTo(node.movie.getTitle()) > 0)
            node.right = insert(node.right, movie);
        else
            return node; // Duplicate titles are not allowed in BST

        // 2. Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Get the balance factor of this ancestor node to check whether this node became unbalanced
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases:

        // Left Left Case
        if (balance > 1 && movie.getTitle().compareTo(node.left.movie.getTitle()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && movie.getTitle().compareTo(node.right.movie.getTitle()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && movie.getTitle().compareTo(node.left.movie.getTitle()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && movie.getTitle().compareTo(node.right.movie.getTitle()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Search for a movie by title
    public Movie search(String title) {
        return search(root, title);
    }

    private Movie search(AVLTreeNode node, String title) {
        if (node == null)
            return null;

        if (title.compareTo(node.movie.getTitle()) < 0)
            return search(node.left, title);
        else if (title.compareTo(node.movie.getTitle()) > 0)
            return search(node.right, title);
        else
            return node.movie;
    }

    // Delete a movie from the AVL tree
    public void delete(String title) {
        root = delete(root, title);
    }

    private AVLTreeNode delete(AVLTreeNode root, String title) {
        if (root == null)
            return root;

        if (title.compareTo(root.movie.getTitle()) < 0)
            root.left = delete(root.left, title);
        else if (title.compareTo(root.movie.getTitle()) > 0)
            root.right = delete(root.right, title);
        else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                AVLTreeNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of the non-empty child
            } else {
                // node with two children: Get the inorder successor (smallest in the right subtree)
                AVLTreeNode temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.movie = temp.movie;

                // Delete the inorder successor
                root.right = delete(root.right, temp.movie.getTitle());
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // Update height of the current node
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Get the balance factor of this node
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // A utility function to find the node with the smallest value
    private AVLTreeNode minValueNode(AVLTreeNode node) {
        AVLTreeNode current = node;

        // Loop down to find the leftmost leaf
        while (current.left != null)
            current = current.left;

        return current;
    }

    // A utility function to do inorder traversal of AVL tree
    public List<Movie> inOrderTraversal() {
        List<Movie> movies = new ArrayList<>();
        inOrderTraversal(root, movies);
        return movies;
    }

    private void inOrderTraversal(AVLTreeNode node, List<Movie> movies) {
        if (node != null) {
            inOrderTraversal(node.left, movies);
            movies.add(node.movie);
            inOrderTraversal(node.right, movies);
        }
    }
}
