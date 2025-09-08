package io.awijaya.lab;

public class MySymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isSymmetricRecursive(root.left, root.right);
    }

    public static boolean isSymmetricRecursive(TreeNode node1, TreeNode node2) {
        if ((node1 == null && node2 != null) || (node1 != null && node2 == null)) {
            return false;
        }
        if ((node1 != null && node2 != null) && node1.val != node2.val) {
            return false;
        }
        if (node1 == null && node2 == null) {
            return true;
        }

        return isSymmetricRecursive(node1.right, node2.left) && isSymmetricRecursive(node1.left, node2.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        System.out.println(isSymmetric(node1));
    }
}
