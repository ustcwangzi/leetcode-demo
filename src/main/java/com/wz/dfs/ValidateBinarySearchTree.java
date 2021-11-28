package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 * @link ../../../../resource/ValidateBinarySearchTree1.jpg
 * Input: root = [2,1,3]
 * Output: true
 *
 * Example 2:
 * @link ../../../../resource/ValidateBinarySearchTree2.jpg
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 104].
 * 2. -2^31 <= Node.val <= 2^31 - 1
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(isValidBST(root));
    }

    /**
     * DFS
     * 维护当前允许的节点值范围 min、max，判断左右子树的节点值都要 大于 min 、小于 max
     */
    public static boolean isValidBST(TreeNode root) {
        return dfs(root.left, Long.MIN_VALUE, root.val) && dfs(root.right, root.val, Long.MAX_VALUE);
    }

    private static boolean dfs(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val > min && root.val < max) {
            return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
        }
        return false;
    }
}
