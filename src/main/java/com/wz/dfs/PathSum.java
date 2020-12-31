package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given the below binary tree and sum = 22,
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(8);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(11, new TreeNode(7), new TreeNode(2));
        right.left = new TreeNode(13);
        right.right = new TreeNode(4, null, new TreeNode(1));
        System.out.println(hasPathSum(root, 22));
    }

    /**
     * DFS
     * 如果当前节点是叶节点并且节点值等于 sum，直接返回true
     * 否则，从 sum 中减去当前节点值，然后判断左子树或右子树是否满足条件
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
