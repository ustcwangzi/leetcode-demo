package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)
 * A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.
 * Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.
 *
 * Example 1:
 * @see ../../../../resource/InsufficientNodesInRootToLeafPaths1.jpg
 * Input: root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 * @see ../../../../resource/InsufficientNodesInRootToLeafPaths2.jpg
 * Output: [1,2,3,4,null,null,7,8,9,null,14]
 */
public class InsufficientNodesInRootToLeafPaths {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(4, new TreeNode(8), new TreeNode(9));
        TreeNode right1 = new TreeNode(-99, new TreeNode(-99), new TreeNode(-99));
        TreeNode left2 = new TreeNode(-99, new TreeNode(12), new TreeNode(13));
        TreeNode right2 = new TreeNode(7, new TreeNode(-99), new TreeNode(14));
        TreeNode left = new TreeNode(2, left1, right1);
        TreeNode right = new TreeNode(3, left2, right2);
        TreeNode root = new TreeNode(1, left, right);
        TreeNode result = sufficientSubset(root, 1);
        TreeNode.traversalPreOrder(result);
    }

    /**
     * DFS
     * 如果某个节点是叶节点，则判断到根节点的路径之和是否小于 limit，如果小于，则直接将其变为 NULL
     * 如果不是叶节点，则递归左子节点和右子节点，如果当前节点变为了叶节点，也将其变为 NULL，因为这表示所有 “根-叶” 路径都小于 limit
     */
    public static TreeNode sufficientSubset(TreeNode root, int limit) {
        dfs(root, limit, 0);
        if (root.left == null && root.right == null && root.val < limit) {
            return null;
        }
        return root;
    }

    private static boolean dfs(TreeNode root, int limit, int sum) {
        if (root == null) {
            return false;
        }
        sum += root.val;
        boolean left = dfs(root.left, limit, sum);
        boolean right = dfs(root.right, limit, sum);
        if (root.left == null && root.right == null) {
            return sum >= limit;
        }
        if (!left) {
            root.left = null;
        }
        if (!right) {
            root.right = null;
        }
        return left || right;
    }
}
