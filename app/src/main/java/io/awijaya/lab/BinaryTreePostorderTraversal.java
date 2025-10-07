package io.awijaya.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 * level: easy
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        List<Integer> leftList = postorderTraversal(root.left);
        result.addAll(leftList);

        List<Integer> rightList = postorderTraversal(root.right);
        result.addAll(rightList);
        result.add(root.val);

        return result;
    }

    public static void main(String[] args) {

    }
}
