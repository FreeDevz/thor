package io.awijaya.test;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    List<TreeNode> children;

    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    void addChild(TreeNode child) {
        this.children.add(child);
    }

    boolean isLeaf() {
        return this.children.isEmpty();
    }
}

public class LowestCommonAncestor {
    // Find LCA of leaf nodes
    public static TreeNode findLCAList(TreeNode root, List<TreeNode> leaves) {
        if(leaves.size() < 2) {
            throw new IllegalArgumentException("leaves size need to be more than 1.");
        }

        TreeNode prev = leaves.get(0);
        TreeNode result = null;
        for (int i=1; i < leaves.size(); i++) {
            TreeNode current = leaves.get(i);
            TreeNode currentCommon = findLCA(root, prev, current);
            if(result == null || currentCommon.val < result.val) {
                result = currentCommon;
            }
            prev = current;
        }

        return result;
    }

    // Find LCA of two leaf nodes
    public static TreeNode findLCA(TreeNode root, TreeNode leaf1, TreeNode leaf2) {
        if (root == null) return null;

        // If current node is one of the target leaves
        if (root == leaf1 || root == leaf2) {
            return root;
        }

        // Search in all children
        List<TreeNode> foundNodes = new ArrayList<>();
        for (TreeNode child : root.children) {
            TreeNode result = findLCA(child, leaf1, leaf2);
            if (result != null) {
                foundNodes.add(result);
            }
        }

        // If both leaves found in different subtrees, current node is LCA
        if (foundNodes.size() >= 2) {
            return root;
        }

        // If found in one subtree, propagate it up
        if (foundNodes.size() == 1) {
            return foundNodes.get(0);
        }

        return null;
    }

    // Alternative: Find LCA by storing paths from root to leaves
    public static TreeNode findLCAWithPath(TreeNode root, TreeNode leaf1, TreeNode leaf2) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();

        findPath(root, leaf1, path1);
        findPath(root, leaf2, path2);

        TreeNode lca = null;
        int i = 0;
        while (i < path1.size() && i < path2.size() && path1.get(i) == path2.get(i)) {
            lca = path1.get(i);
            i++;
        }

        return lca;
    }

    private static boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) return false;

        path.add(root);

        if (root == target) {
            return true;
        }

        for (TreeNode child : root.children) {
            if (findPath(child, target, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        // Create a non-binary tree:
        //          1
        //       /  |  \
        //      2   3    4
        //     / \      |  \
        //    5   6     7   12
        //             / \ \
        //            8   9 10
        //                 /
        //                11

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);

        root.addChild(node2);
        root.addChild(node3);
        root.addChild(node4);
        node2.addChild(node5);
        node2.addChild(node6);
        node4.addChild(node7);
        node4.addChild(node12);
        node7.addChild(node8);
        node7.addChild(node9);
        node7.addChild(node10);
        node10.addChild(node11);

        // Test cases
        System.out.println("=== Method 1: Recursive ===");
        TreeNode lca1 = findLCA(root, node5, node6);
        System.out.println("LCA of leaf 5 and leaf 6: " + (lca1 != null ? lca1.val : "null")); // 2

        TreeNode lca2 = findLCA(root, node5, node8);
        System.out.println("LCA of leaf 5 and leaf 8: " + (lca2 != null ? lca2.val : "null")); // 1

        TreeNode lca3 = findLCA(root, node8, node9);
        System.out.println("LCA of leaf 8 and leaf 9: " + (lca3 != null ? lca3.val : "null")); // 7

        TreeNode lca4 = findLCA(root, node3, node6);
        System.out.println("LCA of leaf 3 and leaf 6: " + (lca4 != null ? lca4.val : "null")); // 1

        TreeNode lca5 = findLCA(root, node8, node11);
        System.out.println("LCA of leaf 8 and leaf 11: " + (lca5 != null ? lca5.val : "null")); // 7

        TreeNode lcaList1 = findLCAList(root, List.of(node5, node8, node11));
        System.out.println("LCA of leaf 5, leaf 8 and leaf 11: " + (lcaList1 != null ? lcaList1.val : "null")); // 1

        TreeNode lcaList2 = findLCAList(root, List.of(node12, node8, node11));
        System.out.println("LCA of leaf 12, leaf 8 and leaf 11: " + (lcaList2 != null ? lcaList2.val : "null")); // 4

        System.out.println("\n=== Method 2: Path-based ===");
        TreeNode lcaWithPath1 = findLCAWithPath(root, node5, node6);
        System.out.println("LCA of leaf 5 and leaf 6: " + (lcaWithPath1 != null ? lcaWithPath1.val : "null")); // 2

        TreeNode lcaWithPath2 = findLCAWithPath(root, node5, node8);
        System.out.println("LCA of leaf 5 and leaf 8: " + (lcaWithPath2 != null ? lcaWithPath2.val : "null")); // 1
    }
}
