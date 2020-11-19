package com.wz.dynamicprogramming;

import com.wz.common.TreeNode;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [3,2,3,null,3,null,1]
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2, null, new TreeNode(3));
        root.right = new TreeNode(3, null, new TreeNode(1));
        System.out.println(rob(root));
    }

    /**
     * DFS
     * result[0] 表示不包含当前节点的最大值，result[1] 表示包含当前节点的最大值
     * 在遍历某个节点时，对其左右子节点进行 DFS，分别得到包含与不包含左子节点的最大值、包含与不包含右子节点的最大值
     * 此时，result[0] 就是左子节点两种情况的较大值加上右子节点两种情况的较大值，因为没有包含当前节点，所有左右子节点都可以包含
     * result[1] 就是不包含左子节点值的最大值加上不包含右子节点值的最大值，再加上当前节点值，因为包含了当前节点，所以左右子节点都不能包含
     */
    public static int rob(TreeNode root) {
        int[] result = dfs(root);
        return Math.max(result[0], result[1]);
    }

    private static int[] dfs(TreeNode root) {
        int[] result = new int[2];
        if (root == null) {
            return result;
        }
        int[] left = dfs(root.left), right = dfs(root.right);
        // 左右子节点都可以选择
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 左右子节点都不能选择
        result[1] = root.val + left[0] + right[0];
        return result;
    }
}
