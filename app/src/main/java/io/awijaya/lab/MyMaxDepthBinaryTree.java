package io.awijaya.lab;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/

public class MyMaxDepthBinaryTree {
    public static int maxDepth(TreeNode root) {
        // Base case: if root is null, depth is 0
        if (root == null) {
            return 0;
        }

        // Recursively find depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // Return maximum depth plus 1 for current node
        return Math.max(leftDepth, rightDepth) + 1;

        // --> Math.max(maxDepth(root.left), Math.max(leftDepth, Math.max(leftDepth, Math.max(leftDepth, rightDepth) + 1)) + 1) + 1;
        // --> Math.max(maxDepth(root.left), Math.max(leftDepth, Math.max(leftDepth, 0 + 1)) + 1) + 1;
        // --> Math.max(maxDepth(root.left), Math.max(leftDepth, Math.max(leftDepth, 1)) + 1) + 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.right = node3;

        System.out.println(maxDepth(node1));
    }
}
