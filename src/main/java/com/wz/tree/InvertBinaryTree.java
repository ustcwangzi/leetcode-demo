package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Invert a binary tree.
 *
 * Example:
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * Output:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class InvertBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode right = new TreeNode(7, new TreeNode(6), new TreeNode(9));
        TreeNode root = new TreeNode(4, left, right);
        TreeNode.traversalPreOrder(invertTree(root));
    }

    /**
     * 递归，没遍历到一个节点，就交换其左右子树
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
