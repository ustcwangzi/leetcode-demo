package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Example 1:
 * @link ../../../../resource/MaximumDepthOfBinaryTree.jpg
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 104].
 * 2. -100 <= Node.val <= 100
 */
public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        System.out.println(maxDepth(root));
    }

    /**
     * DFS
     * 左右子树最大深度 + 1 就是整棵树的深度
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
