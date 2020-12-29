package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * Example 1:
 * @see ../../../../resource/SameTree1.jpg
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 *
 * Example 2:
 * @see ../../../../resource/SameTree2.jpg
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 *
 * Constraints:
 * 1. The number of nodes in both trees is in the range [0, 100].
 * 2. -10^4 <= Node.val <= 10^4
 */
public class SameTree {
    public static void main(String[] args) {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(isSameTree(p, q));
    }

    /**
     * DFS
     * 依次判断根、左子树、右子树
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        return p != null && q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
