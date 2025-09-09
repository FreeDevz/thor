package io.awijaya.lab;

public class ClaudeBinarySearchTree {
    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    private TreeNode root;

    public ClaudeBinarySearchTree() {
        this.root = null;
    }

    // Public method to insert a value
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    // Private recursive method for insertion
    private TreeNode insertRecursive(TreeNode node, int data) {
        // Base case: if node is null, create new node
        if (node == null) {
            return new TreeNode(data);
        }

        // Insert in left subtree if data is smaller
        if (data < node.data) {
            node.left = insertRecursive(node.left, data);
        }
        // Insert in right subtree if data is larger
        else if (data > node.data) {
            node.right = insertRecursive(node.right, data);
        }
        // If data equals node.data, don't insert (no duplicates)

        return node;
    }

    // Public method to search for a value
    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    // Private recursive method for searching
    private boolean searchRecursive(TreeNode node, int data) {
        // Base case: node is null (value not found)
        if (node == null) {
            return false;
        }

        // Value found
        if (data == node.data) {
            return true;
        }

        // Search in left subtree if data is smaller
        if (data < node.data) {
            return searchRecursive(node.left, data);
        }
        // Search in right subtree if data is larger
        else {
            return searchRecursive(node.right, data);
        }
    }

    // Public method to delete a value
    public void delete(int data) {
        root = deleteRecursive(root, data);
    }

    // Private recursive method for deletion
    private TreeNode deleteRecursive(TreeNode node, int data) {
        // Base case: node is null
        if (node == null) {
            return null;
        }

        // Find the node to delete
        if (data < node.data) {
            node.left = deleteRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRecursive(node.right, data);
        } else {
            // Node to be deleted found

            // Case 1: Node has no children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Case 3: Node has two children
            // Find the inorder successor (smallest value in right subtree)
            TreeNode successor = findMin(node.right);

            // Replace node's data with successor's data
            node.data = successor.data;

            // Delete the successor
            node.right = deleteRecursive(node.right, successor.data);
        }

        return node;
    }

    // Helper method to find minimum value node
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Public method for inorder traversal
    public void inorderTraversal() {
        System.out.print("Inorder: ");
        inorderRecursive(root);
        System.out.println();
    }

    // Private recursive method for inorder traversal
    // Left -> Root -> Right
    private void inorderRecursive(TreeNode node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + " ");
            inorderRecursive(node.right);
        }
    }

    // Public method for preorder traversal
    public void preorderTraversal() {
        System.out.print("Preorder: ");
        preorderRecursive(root);
        System.out.println();
    }

    // Private recursive method for preorder traversal
    private void preorderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        }
    }

    // Public method for postorder traversal
    public void postorderTraversal() {
        System.out.print("Postorder: ");
        postorderRecursive(root);
        System.out.println();
    }

    // Private recursive method for postorder traversal
    // Left -> Right -> Root
    private void postorderRecursive(TreeNode node) {
        if (node != null) {
            postorderRecursive(node.left);
            postorderRecursive(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Find maximum value in the tree
    public int findMax() {
        if (root == null) {
            throw new RuntimeException("Tree is empty");
        }
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    // Find minimum value in the tree
    public int findMinValue() {
        if (root == null) {
            throw new RuntimeException("Tree is empty");
        }
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    // Check if tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Get height of the tree
    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(TreeNode node) {
        if (node == null) {
            return -1; // Height of empty tree is -1
        }
        return 1 + Math.max(getHeightRecursive(node.left), getHeightRecursive(node.right));
    }

    // Main method with example usage
    public static void main(String[] args) {
        ClaudeBinarySearchTree bst = new ClaudeBinarySearchTree();

        // Insert values
        System.out.println("Inserting values: 50, 30, 70, 20, 40, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Display traversals
        bst.inorderTraversal();    // Should print: 20 30 40 50 60 70 80
        bst.preorderTraversal();   // Should print: 50 30 20 40 70 60 80
        bst.postorderTraversal();  // Should print: 20 40 30 60 80 70 50

        // Search operations
        System.out.println("Search for 40: " + bst.search(40)); // true
        System.out.println("Search for 25: " + bst.search(25)); // false

        // Find min and max
        System.out.println("Minimum value: " + bst.findMinValue()); // 20
        System.out.println("Maximum value: " + bst.findMax());      // 80

        // Tree height
        System.out.println("Tree height: " + bst.getHeight());     // 2

        // Delete operations
        System.out.println("\nDeleting 30 (node with two children):");
        bst.delete(30);
        bst.inorderTraversal(); // Should print: 20 40 50 60 70 80

        System.out.println("Deleting 20 (leaf node):");
        bst.delete(20);
        bst.inorderTraversal(); // Should print: 40 50 60 70 80

        System.out.println("Deleting 50 (root node):");
        bst.delete(50);
        bst.inorderTraversal(); // Should print: 40 60 70 80
    }
}
