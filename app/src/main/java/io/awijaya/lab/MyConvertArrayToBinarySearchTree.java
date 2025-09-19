package io.awijaya.lab;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 * level: easy
 */
public class MyConvertArrayToBinarySearchTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildBST(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBST(int[] nums, int left, int right) {
        // Base case: no more elements to process
        if (left > right) { // needed to bigger than, not bigger equal condition, as we still need to process when left is equal to right.
            return null;
        }

        // Choose middle element as root to ensure height balance
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build left and right subtrees
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    public static void main(String[] args) {
        TreeNode nodeZero = new TreeNode(0);
        TreeNode nodeMinusThree = new TreeNode(-3);
        TreeNode nodeNine = new TreeNode(9);
        TreeNode nodeMinusTen = new TreeNode(-10);
        TreeNode nodeFive = new TreeNode(5);

        nodeZero.left = nodeMinusThree;
        nodeZero.right = nodeNine;
        nodeMinusThree.left = nodeMinusTen;
        nodeNine.left = nodeFive;

        int[] test2 = {1, 3};
        TreeNode result2 = sortedArrayToBST(test2);
        System.out.println(result2);
    }
}
