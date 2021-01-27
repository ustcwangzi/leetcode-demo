package com.wz.bfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 * Example 1:
 * @see ../../../../resource/MaximumLevelSumOfBinaryTree.jpg
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Example 2:
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 * Constraints:
 * 1. The number of nodes in the tree is in the range [1, 104].
 * 2. -10^5 <= Node.val <= 10^5
 */
public class MaximumLevelSumOfBinaryTree {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(7, new TreeNode(7), new TreeNode(-8));
        TreeNode root = new TreeNode(1, left, new TreeNode(0));
        System.out.println(maxLevelSum(root));
    }

    /**
     * BFS
     * 直接层次遍历
     */
    public static int maxLevelSum(TreeNode root) {
        int result = 1, level = 0, maxSum = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size(), sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            if (sum > maxSum) {
                maxSum = sum;
                result = level;
            }
        }
        return result;
    }
}
