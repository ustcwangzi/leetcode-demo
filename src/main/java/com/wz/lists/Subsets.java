package com.wz.lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(subsets(nums));
    }

    /**
     * 因为每个数字是否存在在子集中，只有是和否两个选择，所以可以将处理每一个数字作为一级二叉树的分支选择
     * 因此可以构造一颗二叉树，例如[1,2,3]，构造的二叉树如下(左子树表示选择该层处理的元素，右子树不选择)，最后得到的叶子节点就是子集
     *                         []     处理1
     *                    /          \
     *                   /            \
     *                  /              \
     *               [1]                []   处理2
     *            /       \           /    \
     *           /         \         /      \
     *        [1 2]       [1]       [2]     []   处理3
     *       /     \     /   \     /   \    / \
     *   [1 2 3] [1 2] [1 3] [1] [2 3] [2] [3] []
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>((int) Math.pow(2, nums.length));
        dfs(nums, 0, new LinkedList<>(), result);
        return result;
    }

    private static void dfs(int[] nums, int index, List<Integer> group, List<List<Integer>> result) {
        // 到达子节点
        if (index >= nums.length) {
            result.add(group);
            return;
        }

        List<Integer> selected = new ArrayList<>(group);
        selected.add(nums[index]);
        // 选择当前元素，即遍历左子树
        dfs(nums, index + 1, selected, result);

        // 不选择当前元素，即遍历右子树
        dfs(nums, index + 1, group, result);
    }
}
