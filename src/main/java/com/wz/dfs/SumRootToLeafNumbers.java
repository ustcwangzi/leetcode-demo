package com.wz.dfs;

import com.wz.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 * Example 2:
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(sumNumbers(root));
    }

    /**
     * DFS
     * 思路与 {@link PathSumII} 类似
     * 使用 path 收集所有的路径，存在 result 中，然后遍历 result 将路径代表的值进行叠加
     */
    public static int sumNumbers(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(root, result, new LinkedList<>());

        int sum = 0;
        for (List<Integer> path : result) {
            StringBuilder builder = new StringBuilder();
            path.forEach(builder::append);
            sum += Integer.parseInt(builder.toString());
        }
        return sum;
    }

    private static void dfs(TreeNode root, List<List<Integer>> result, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            result.add(new LinkedList<>(path));
        }
        dfs(root.left, result, path);
        dfs(root.right, result, path);
        path.remove(path.size() - 1);
    }
}
