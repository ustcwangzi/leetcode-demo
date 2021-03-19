package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
 * A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 *
 * Example 2:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * Given tree t:
 *    4
 *   / \
 *  1   2
 * Return false.
 */
public class SubtreeOfAnotherTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        TreeNode root = new TreeNode(3, left, new TreeNode(5));
        System.out.println(isSubtree(root, left));

        left = new TreeNode(4, new TreeNode(1), new TreeNode(2, new TreeNode(0), null));
        TreeNode s = new TreeNode(3, left, new TreeNode(5));
        TreeNode t = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        System.out.println(isSubtree(s, t));
    }

    /**
     * 递归
     * s 与 t 同构、s 左子树与 t 同构、s 右子树与 t 同构
     */
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return equals(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean equals(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && equals(s.left, t.left) && equals(s.right, t.right);
    }
}
