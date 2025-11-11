package io.awijaya.lab3;

/**
 * https://leetcode.com/problems/closest-binary-search-tree-value/description/
 * level: easy
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class ClosestBinaryTreeSearchValue {

    public int closestValue(TreeNode root, double target) {
        int ans = root.val;
        double smallestDiff = Double.MAX_VALUE;
        while (root != null) {
            double diff = Math.abs(root.val - target);
            if (diff < smallestDiff || (diff == smallestDiff && root.val < ans)) {
                smallestDiff = diff;
                ans = root.val;
            }
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ClosestBinaryTreeSearchValue closestBinaryTreeSearchValue = new ClosestBinaryTreeSearchValue();

        // root = [4,2,5,1,3], target = 3.714286, output = 4
        TreeNode node = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);

        node.left = node2;
        node.right = node3;
        node2.left = node4;
        node2.right = node5;

        System.out.println(closestBinaryTreeSearchValue.closestValue(node, 3.714286));
    }
}
