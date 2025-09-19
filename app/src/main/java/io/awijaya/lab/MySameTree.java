package io.awijaya.lab;

/**
 * https://leetcode.com/problems/same-tree/
 * level: easy
 */
public class MySameTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q == null || p == null && q != null) {
            return false;
        }
        if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
            boolean leftUnequal = isSameTree(p.left, q.left);
            if (!leftUnequal) return false;
            return isSameTree(p.right, q.right);
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);

        TreeNode treeNode2 = new TreeNode(1);
        treeNode2.left = null;
        treeNode2.right = new TreeNode(2);

        System.out.println(isSameTree(treeNode1, treeNode2));
    }
}
