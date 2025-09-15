package io.awijaya.lab;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/description/
 */
public class MyCountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Approach 1: Recursive Height Comparison (Optimal)
     *
     * Leverage the complete binary tree property: if left and right subtrees have same height, the
     * left subtree is perfect and can be calculated using 2^height - 1.
     *
     * Time Complexity: O(log²n) Space Complexity: O(logn)
     */
    public static class RecursiveHeightComparisonApproach {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);

            if (leftHeight == rightHeight) {
                // Left subtree is perfect binary tree
                // Total nodes = left subtree nodes (2^leftHeight - 1) + root (1) + right subtree
                // nodes
                return (1 << leftHeight) + countNodes(root.right);
            } else {
                // Right subtree is perfect binary tree
                // Total nodes = right subtree nodes (2^rightHeight - 1) + root (1) + left subtree
                // nodes
                return (1 << rightHeight) + countNodes(root.left);
            }
        }

        private int getHeight(TreeNode node) {
            int height = 0;
            while (node != null) {
                height++;
                node = node.left;
            }
            return height;
        }
    }

    public static void main(String[] args) {
        System.out.println(1 << 4);
    }
}
