package com.wz.dynamicprogramming;

import com.wz.common.TreeNode;

/**
 * Given a binary tree root, a ZigZag path for a binary tree is defined as follow:
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right then move to the right child of the current node otherwise move to the left child.
 * Change the direction from right to left or right to left.
 * Repeat the second and third step until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 * Return the longest ZigZag path contained in that tree.
 *
 * Example 1:
 * @link ../../../../resource/LongestZigZagPathInBinaryTree1.jpg
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 *
 * Example 2:
 * @link ../../../../resource/LongestZigZagPathInBinaryTree1.jpg
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 *
 * Constraints:
 * 1. Each tree has at most 50000 nodes..
 * 2. Each node's value is between [1, 100].
 */
public class LongestZigZagPathInBinaryTree {
    private static int MAX_STEP = 0;

    /**
     * DFS，使用 isLeft 控制向左还是向右
     */
    public static int longestZigZag(TreeNode root) {
        dfs(root, true, 0);
        dfs(root, false, 0);
        return MAX_STEP;
    }

    private static void dfs(TreeNode root, boolean isLeft, int step) {
        if (root == null) {
            return;
        }
        MAX_STEP = Math.max(MAX_STEP, step);
        if (isLeft) {
            dfs(root.left, false, step + 1);
            dfs(root.right, true, 1);
        } else {
            dfs(root.right, true, step + 1);
            dfs(root.left, false, 1);
        }
    }
}
