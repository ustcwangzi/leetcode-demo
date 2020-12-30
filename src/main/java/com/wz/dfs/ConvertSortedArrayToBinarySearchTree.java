package com.wz.dfs;

import com.wz.common.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of
 * the two subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted array: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        TreeNode.traversalPreOrder(sortedArrayToBST(new int[]{1, 2}));
    }

    /**
     * DFS
     * 将数组从中间分为两部分，中间节点为根，左半部分作为左子树，右半部分作为右子树
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private static TreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, start, mid - 1);
        root.right = build(nums, mid + 1, end);
        return root;
    }
}
