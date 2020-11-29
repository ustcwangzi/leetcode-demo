package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Note:
 * 1. 1 <= k <= len(nums) <= 16.
 * 2. 0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }

    /**
     * DFS
     * 先求出数组的所有数字之和 total，如果 total 不能整除 k 直接返回false
     * 用 visited[] 数组来记录哪些数字已经被选中了，然后调用递归函数，目标是组成 k 个子集合，每个子集合之和为 target = total/k
     * 用 start 表示从数组的某个位置开始查找，curSum 为当前子集合之和，在递归中，如果 k=0，说明已全部找到，直接返回true
     * 如果 curSum 等于 target，再次调用递归，此时传入 k-1，同时 start 和 curSum 都重置为 0，表示继续找下一个
     * 否则就从 start 开始遍历数组，如果当前数字已经访问过了则直接跳过，否则标记为已访问，
     * 然后调用递归函数，k 保持不变，因为还在累加当前的子集合，start 传入 i+1，curSum 传入 curSum+nums[i]，因为要累加当前的数字，
     * 如果递归函数返回 true了，则直接返回true。否则就将当前数字重置为未访问的状态继续遍历下一个数字
     */
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int total = Arrays.stream(nums).sum();
        return total % k == 0 && dfs(nums, k, total / k, 0, 0, new boolean[nums.length]);
    }

    private static boolean dfs(int[] nums, int k, int target, int start, int curSum, boolean[] visited) {
        // 已找到 k 个子数组
        if (k == 0) {
            return true;
        }
        if (curSum > target) {
            return false;
        }
        if (curSum == target) {
            // 已找到一个子数组，继续寻找下一个
            return dfs(nums, k - 1, target, 0, 0, visited);
        }

        for (int i = start; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            // 选中当前的数字并累加
            visited[i] = true;
            if (dfs(nums, k, target, i + 1, curSum + nums[i], visited)) {
                return true;
            }
            // 不选择当前数字
            visited[i] = false;
        }
        return false;
    }
}
