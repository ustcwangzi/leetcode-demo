package com.wz.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class SubsetsII {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }

    /**
     * 思路与{@link Subsets}类似
     * 存在重复元素，因此需要先排序，另外，当遇到重复元素时，需要跳过
     * 例如[1,2,3]，处理第三个元素2时，因为前面已经处理了一次2，所有第三层中，只在已经添加过2的集合{1,2}、{2}上再添加2
     * 假设下面还有一个2，那么我们只在第四层的包含两个2的集合{1,2,2}、{2,2}上再添加2，其它都不添加
     *                          []     处理1
     *                    /          \
     *                   /            \
     *                  /              \
     *               [1]                []   处理2
     *            /       \           /    \
     *           /         \         /      \
     *        [1 2]       [1]       [2]     []   处理2
     *       /     \     /   \     /   \    / \
     *   [1 2 2] [1 2]  -    [1] [2 2] [2] -   []
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.parallelSort(nums);
        List<List<Integer>> result = new ArrayList<>((int) Math.pow(2, nums.length));
        dfs(nums, 0, new LinkedList<>(), result);
        return result;
    }

    private static void dfs(int[] nums, int index, List<Integer> group, List<List<Integer>> result) {
        result.add(new ArrayList<>(group));
        for (int i = index; i < nums.length; i++) {
            // 检查是否重复添加
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            // 选择当前元素
            group.add(nums[i]);
            dfs(nums, i + 1, group, result);
            // 不选择当前元素
            group.remove(group.size() - 1);
        }
    }
}
