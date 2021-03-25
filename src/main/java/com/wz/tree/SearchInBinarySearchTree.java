package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * You are given the root of a binary search tree (BST) and an integer val.
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
 * If such a node does not exist, return null.
 *
 * Example 1:
 *       4
 *     /   \
 *    2     7
 *   / \
 *  1   3
 * Input: root = [4,2,7,1,3], val = 2
 * Output: [2,1,3]
 *
 * Example 2:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: []
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 5000].
 * 2. 1 <= Node.val <= 10^7
 * 3. root is a binary search tree.
 * 4. 1 <= val <= 10^7
 */
public class SearchInBinarySearchTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode root = new TreeNode(4, left, new TreeNode(7));
        TreeNode result = searchBST(root, 2);
        System.out.println(result == null ? null : result.val);
    }

    /**
     * 根据 BST 的性质直接进行递归查找即可
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }
}
