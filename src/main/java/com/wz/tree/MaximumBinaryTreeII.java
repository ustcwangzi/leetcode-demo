package com.wz.tree;

import com.wz.common.TreeNode;

/**
 * We are given the root node of a maximum tree: a tree where every node has a value greater than any other value in its subtree.
 * Note that we were not given A directly, only a root node root = Construct(A).
 * Suppose B is a copy of A with the value val appended to it.  It is guaranteed that B has unique values.
 * Return Construct(B).
 *
 * Example 1:
 * @link ../../../../resource/MaximumBinaryTreeII1.jpg
 * Input: root = [4,1,3,null,null,2], val = 5
 * Output: [5,4,null,1,3,null,null,2]
 * Explanation: A = [1,4,2,3], B = [1,4,2,3,5]
 *
 * Example 2:
 * @link ../../../../resource/MaximumBinaryTreeII2.jpg
 * Input: root = [5,2,4,null,1], val = 3
 * Output: [5,2,4,null,1,null,3]
 * Explanation: A = [2,1,5,4], B = [2,1,5,4,3]
 *
 * Example 3:
 * @link ../../../../resource/MaximumBinaryTreeII3.jpg
 * Input: root = [5,2,3,null,1], val = 4
 * Output: [5,2,4,null,1,3]
 * Explanation: A = [2,1,5,3], B = [2,1,5,3,4]
 */
public class MaximumBinaryTreeII {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, null, new TreeNode(1));
        TreeNode root = new TreeNode(5, left, new TreeNode(4));
        TreeNode.traversalPreOrder(insertIntoMaxTree(root, 3));

        System.out.println();

        root = new TreeNode(5, left, new TreeNode(3));
        TreeNode.traversalPreOrder(insertIntoMaxTree(root, 4));
    }

    /**
     * 递归
     * 如果根节点为空或者当前节点大于根节点，则将根节点作为当前节点的左子树，否则在右子树中寻找插入位置
     */
    public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (root == null || root.val < val) {
            node.left = root;
            return node;
        }

        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}
