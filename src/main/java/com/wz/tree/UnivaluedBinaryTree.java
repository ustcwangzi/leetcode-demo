package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * A binary tree is univalued if every node in the tree has the same value.
 * Return true if and only if the given tree is univalued.
 *
 * Example 1:
 *      1
 *    /   \
 *   1     1
 *  / \     \
 * 1   1     1
 * Input: [1,1,1,1,1,null,1]
 * Output: true
 * Example 2:
 *       2
 *     /   \
 *    2     2
 *   / \
 *  5   2
 * Input: [2,2,2,5,2]
 * Output: false
 *
 * Note:
 * 1. The number of nodes in the given tree will be in the range [1, 100].
 * 2. Each node's value will be an integer in the range [0, 99].
 */
public class UnivaluedBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1, new TreeNode(1), new TreeNode(1));
        TreeNode right = new TreeNode(1, null, new TreeNode(1));
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(isUnivalTree(root));

        left = new TreeNode(2, new TreeNode(5), new TreeNode(2));
        root = new TreeNode(2, left, new TreeNode(2));
        System.out.println(isUnivalTree(root));
    }

    /**
     * 递归，子树节点值与根节点不等则返回 false
     */
    public static boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}
