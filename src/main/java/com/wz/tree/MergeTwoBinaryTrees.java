package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * You are given two binary trees root1 and root2.
 * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 * You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
 * Return the merged tree.
 * Note: The merging process must start from the root nodes of both trees.
 *
 * Example 1:
 * @link ../../../../resource/CinemaSeatAllocation.jpg
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 *
 * Constraints:
 * 1. The number of nodes in both trees is in the range [0, 2000].
 * 2. -10^4 <= Node.val <= 10^4
 */
public class MergeTwoBinaryTrees {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3, new TreeNode(5), null);
        TreeNode root1 = new TreeNode(1, left1, new TreeNode(2));
        TreeNode left2 = new TreeNode(1, null, new TreeNode(4));
        TreeNode right2 = new TreeNode(3, null, new TreeNode(7));
        TreeNode root2 = new TreeNode(2, left2, right2);
        TreeNode.traversalPreOrder(mergeTrees(root1, root2));
    }

    /**
     * 把 root2 合并到 root1
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }

        root1.val += root2.val;

        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}
