package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * Example 1:
 * @see ../../../../resource/BalancedBinaryTree1.jpg
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 *
 * Example 2:
 * @see ../../../../resource/BalancedBinaryTree2.jpg
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 5000].
 * 2. -10^4 <= Node.val <= 10^4
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        System.out.println(isBalanced(root));
    }

    /**
     * DFS
     * 左右子树高度之差不能大于1
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
