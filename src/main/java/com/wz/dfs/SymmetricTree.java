package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * Follow up: Solve it both recursively and iteratively.
 */
public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        root.right = new TreeNode(2, new TreeNode(4), new TreeNode(3));
        System.out.println(isSymmetric(root));
    }

    /**
     * DFS
     * 以下节点均需要相等：左子树的根节点和右子树的根节点、左子树的左孩子和右子树的右孩子、左子树的右孩子和右子树的左孩子
     */
    public static boolean isSymmetric(TreeNode root) {
        return root == null || dfs(root.left, root.right);
    }

    private static boolean dfs(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        return node1 != null && node2 != null && node1.val == node2.val && dfs(node1.left, node2.right) && dfs(node1.right, node2.left);
    }
}
