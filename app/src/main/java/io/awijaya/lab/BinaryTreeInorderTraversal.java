package io.awijaya.lab;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // Private recursive method for inorder traversal
    // Root -> Left -> Right
    public void preorderRecursive(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preorderRecursive(node.left);
            preorderRecursive(node.right);
        } else {
            System.out.print("null ");
        }
    }
}

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * level: easy
 */
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Push all left nodes to stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Pop and visit current node
            current = stack.pop();
            result.add(current.val);

            // Move to right subtree
            current = current.right;
        }

        return result;
    }

    public static TreeNode insert(TreeNode root, Integer data) {
        if (root == null) {
            return new TreeNode(data);
        }
        if (data == null) {

        }
//        if (data < root.val) {
//            root.left = insert(root.left, data);
//        }
//        if (data > root.val) {
//            root.right = insert(root.right, data);
//        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] test1 = {1, null, 2, 3};
        TreeNode treeNode1 = null;
        for (Integer data : test1) {
            if (data != null) {
                treeNode1 = insert(treeNode1, data);
            }
        }

    }
}
