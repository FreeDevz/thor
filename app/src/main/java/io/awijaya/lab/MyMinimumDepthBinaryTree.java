package io.awijaya.lab;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * level: easy
 */
public class MyMinimumDepthBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        if (leftDepth > 0 && rightDepth > 0) {
            return Math.min(leftDepth, rightDepth) + 1;
        } else {
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    /**
     * Approach 1: Recursive DFS (Optimal Solution)
     *
     * This is the most intuitive solution using depth-first search. We recursively find the minimum
     * depth of left and right subtrees, but we need to handle the case where one subtree is null
     * (we should not consider it as depth 0).
     *
     * @param root the root of the binary tree
     * @return the minimum depth of the tree
     */
    public int minDepthRecursiveByCursor(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // If both children are null, this is a leaf node
        if (root.left == null && root.right == null) {
            return 1;
        }

        // If left child is null, only consider right subtree
        if (root.left == null) {
            return minDepthRecursiveByCursor(root.right) + 1;
        }

        // If right child is null, only consider left subtree
        if (root.right == null) {
            return minDepthRecursiveByCursor(root.left) + 1;
        }

        // Both children exist, find minimum of both subtrees
        return Math.min(minDepthRecursiveByCursor(root.left), minDepthRecursiveByCursor(root.right)) + 1;
    }
}
