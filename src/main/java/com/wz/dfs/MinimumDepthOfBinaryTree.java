package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 *
 * Example 1:
 * @see ../../../../resource/MinimumDepthOfBinaryTree.jpg
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 *
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [0, 105].
 * 2. -1000 <= Node.val <= 1000
 */
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        System.out.println(minDepth(root));
    }

    /**
     * DFS
     * 和 {@link MaximumDepthOfBinaryTree} 类似，只是需要考虑左右子树为空的情况，不然会返回1
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 左右子树都为空
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 左子树为空
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        // 右子树为空
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}
