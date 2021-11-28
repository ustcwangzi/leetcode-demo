package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.
 *
 * Example 1:
 * @link ../../../../resource/IncreasingOrderSearchTree.jpg
 * Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 * Constraints:
 * 1. The number of nodes in the given tree will be in the range [1, 100].
 * 2. 0 <= Node.val <= 1000
 */
public class IncreasingOrderSearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode result = increasingBST(root);
        TreeNode.traversalPreOrder(result);
    }

    private static TreeNode dummy = new TreeNode(0), cur = dummy;

    /**
     * 中序遍历，将当前遍历到的节点设置为 cur 节点的右孩子
     */
    public static TreeNode increasingBST(TreeNode root) {
        if (root.left != null) {
            increasingBST(root.left);
        }

        cur.right = new TreeNode(root.val);
        cur = cur.right;

        if (root.right != null) {
            increasingBST(root.right);
        }
        return dummy.right;
    }
}
