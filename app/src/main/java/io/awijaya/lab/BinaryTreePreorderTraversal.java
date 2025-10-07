package io.awijaya.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * level: easy
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        addRecursive(root, result);
        return result;
    }

    public List<Integer> addRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return result;

        result.add(node.val);
        addRecursive(node.left, result);
        addRecursive(node.right, result);

        return result;
    }

    public static void main(String[] args) {

    }
}
