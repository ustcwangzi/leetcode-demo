package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
 *
 * Example 1:
 * @link ../../../../resource/BinaryTreePruning1.jpg
 * Input: [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 *
 * Example 2:
 * @link ../../../../resource/BinaryTreePruning2.jpg
 * Input: [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 *
 * Note:
 * 1. The binary tree will have at most 200 nodes.
 * 2. The value of each node will only be 0 or 1.
 */
public class BinaryTreePruning {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(0, new TreeNode(0), new TreeNode(1));
        TreeNode root = new TreeNode(1, null, right);
        TreeNode.traversalPreOrder(pruneTree(root));

        System.out.println();

        TreeNode left = new TreeNode(0, new TreeNode(0), new TreeNode(0));
        right = new TreeNode(1, new TreeNode(0), new TreeNode(1));
        root = new TreeNode(1, left, right);
        TreeNode.traversalPreOrder(pruneTree(root));
    }

    /**
     * 移除都是 0 的子树
     * 递归后序遍历，左右子树都为空并且当前节点值为 0，则将当前节点删除，即置为 null
     */
    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left = pruneTree(root.left);
        }
        if (root.right != null) {
            root.right = pruneTree(root.right);
        }
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}
