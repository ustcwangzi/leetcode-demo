package com.wz.dynamicprogramming;

import com.wz.common.TreeNode;

/**
 * Given a binary tree root, the task is to return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 * @see ../../../../resource/MaximumSumBSTInBinaryTree1.jpg
 * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * Output: 20
 * Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 *
 * Example 2:
 * @see ../../../../resource/MaximumSumBSTInBinaryTree2.jpg
 * Input: root = [4,3,null,1,2]
 * Output: 2
 * Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 *
 * Constraints:
 * 1. The given binary tree will have between 1 and 40000 nodes.
 * 2. Each node's value is between [-4 * 10^4 , 4 * 10^4].
 */
public class MaximumSumBSTInBinaryTree {
    public int maxSumBST(TreeNode root) {
        int[] sum = new int[1];
        helper(root, sum);

        return sum[0];
    }

    /**
     * 递归函数：参数为当前结点，返回值为三元组，以当前结点为根结点的二叉搜索树的总和，子树中的最小值和最大值
     * 分别递归左右儿子结点，得到返回值，如果不存在，则定义返回值为默认值
     * 判断左右子树和当前结点是否构成二叉搜索树，即当前结点的值是否大于左子树最大值，以及小于右子树最小值
     * 如果可以，则更新答案，然后返回新的三元组。否则，返回一个表示非法的二叉搜索树的三元组
     */
    private int[] helper(TreeNode root, int[] max) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = helper(root.left, max), right = helper(root.right, max);
        if (root.val > left[1] && root.val < right[0]) {
            int sum = root.val + left[2] + right[2];
            max[0] = Math.max(max[0], sum);
            return new int[]{Math.min(root.val, left[0]), Math.max(root.val, right[1]), sum};
        }

        return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
    }
}
