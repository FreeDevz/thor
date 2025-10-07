package io.awijaya.lab;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * level: easy
 */
public class BalancedBinaryTree {
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Helper method to check height and balance simultaneously. Returns -1 if the subtree is
     * unbalanced, otherwise returns the height.
     */
    private static int checkHeight(TreeNode node) {
        // Base case: null node has height 0
        if (node == null) {
            return 0;
        }

        // Check left subtree height
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is unbalanced
        }

        // Check right subtree height
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is unbalanced
        }

        // Check if current node is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node is unbalanced
        }

        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        // [3,9,20,null,null,15,7]
//        TreeNode node1 = new TreeNode(3);
//        TreeNode node2 = new TreeNode(9);
//        TreeNode node3 = new TreeNode(20);
//        TreeNode node4 = new TreeNode(15);
//        TreeNode node5 = new TreeNode(7);
//
//        node1.left = node2;
//        node1.right = node3;
//        node3.left = node4;
//        node3.right = node5;
//
//        System.out.println(isBalanced(node1)); // true

        // [1,2,2,3,3,null,null,4,4]
//        TreeNode test2node1 = new TreeNode(1);
//        TreeNode test2node2 = new TreeNode(2);
//        TreeNode test2node3 = new TreeNode(2);
//        TreeNode test2node4 = new TreeNode(3);
//        TreeNode test2node5 = new TreeNode(3);
//        TreeNode test2node6 = new TreeNode(4);
//        TreeNode test2node7 = new TreeNode(4);
//
//        test2node1.left = test2node2;
//        test2node1.right = test2node3;
//        test2node2.left = test2node4;
//        test2node2.right = test2node5;
//        test2node4.left = test2node6;
//        test2node4.right = test2node7;
//
//        System.out.println(isBalanced(test2node1)); // false

        // [1,2,2,3,null,null,3,4,null,null,4]
        TreeNode test2node1 = new TreeNode(1);
        TreeNode test2node2 = new TreeNode(2);
        TreeNode test2node3 = new TreeNode(2);
        TreeNode test2node4 = new TreeNode(3);
        TreeNode test2node5 = new TreeNode(3);
        TreeNode test2node6 = new TreeNode(4);
        TreeNode test2node7 = new TreeNode(4);

        test2node1.left = test2node2;
        test2node1.right = test2node3;

        test2node2.left = test2node4;

        test2node3.right = test2node5;

        test2node5.left = test2node6;
        test2node6.right = test2node7;

        System.out.println(isBalanced(test2node1)); // false
    }
}
