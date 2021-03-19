package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 *      1
 *     / \
 *    2   3
 *   / \
 *  4   5
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 10^4].
 * 2. -100 <= Node.val <= 100
 */
public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(4, new TreeNode(2), new TreeNode(5));
        TreeNode root = new TreeNode(1, left, new TreeNode(3));
        System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTree(root));
    }

    int result = 0;

    /**
     * 就是要求左右子树的最大高度
     */
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return result;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left), right = dfs(root.right);
        result = Math.max(result, left + right);
        return Math.max(left, right) + 1;
    }
}
