package com.wz.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 6
 * 2. -10 <= nums[i] <= 10
 * 3. All the integers of nums are unique.
 */
public class Permutations {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }

    /**
     * 全排列
     * 本质是记录当前已经拼出的个数，一旦达到了数组的长度，就将当前记录存在结果集中
     * 每次要从 0 开始遍历，因为这是求全排列，每个位置都可以放任意数字，这样会有个问题，数字有可能被重复使用，
     * 由于全排列是不能重复使用数字的，所以需要用一个 visited 数组来标记某个数字是否使用过
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(nums, new boolean[nums.length], result, new ArrayList<>());
        return result;
    }

    private static void dfs(int[] nums, boolean[] visited, List<List<Integer>> result, List<Integer> group) {
        if (group.size() == nums.length) {
            result.add(new ArrayList<>(group));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 开始遍历 nums[i]
            visited[i] = true;
            group.add(nums[i]);
            dfs(nums, visited, result, group);
            // 结束遍历 nums[i]
            visited[i] = false;
            group.remove(group.size() - 1);
        }
    }
}
