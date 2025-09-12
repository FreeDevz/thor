package io.awijaya.lab;


// https://leetcode.com/problems/path-sum/
public class MyPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        int nodeValue = root.val;
        targetSum -= nodeValue;

        if (root.left == null & root.right == null && targetSum == 0) {
            return true;
        } else {
            return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
        }
    }
}
