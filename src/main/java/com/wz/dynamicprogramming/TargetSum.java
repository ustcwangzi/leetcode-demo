package com.wz.dynamicprogramming;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 * Constraints:
 * 1. The length of the given array is positive and will not exceed 20.
 * 2. The sum of elements in the given array will not exceed 1000.
 * 3. Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    /**
     * DFS
     * 分别对目标值进行加上当前数字调用递归、减去当前数字调用递归，当所有数字遍历完成并且目标值为 0 ，则结果加 1
     */
    public static int findTargetSumWays(int[] nums, int S) {
        int[] result = new int[1];
        dfs(nums, 0, S, result);
        return result[0];
    }

    private static void dfs(int[] nums, int start, int target, int[] result) {
        if (start == nums.length && target == 0) {
            result[0]++;
        }
        if (start >= nums.length) {
            return;
        }
        // 加上当前值
        dfs(nums, start + 1, target + nums[start], result);
        // 减去当前值
        dfs(nums, start + 1, target - nums[start], result);
    }
}
