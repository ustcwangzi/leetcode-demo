package com.wz.dfs;

import java.util.Arrays;

/**
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has,
 * please find out a way you can make one square by using up all those matchsticks. You should not break any stick,
 * but you can link them up, and each matchstick must be used exactly one time.
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false,
 * to represent whether you could make one square using all the matchsticks the little match girl has.
 *
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 *
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 *
 * Note:
 * 1. The length sum of the given matchsticks is in the range of 0 to 10^9.
 * 2. The length of the given matchstick array will not exceed 15.
 */
public class MatchsticksToSquare {
    public static void main(String[] args) {
        System.out.println(makesquare(new int[]{1, 1, 2, 2, 2}));
    }

    /**
     * 与 {@link com.wz.dynamicprogramming.PartitionToKEqualSumSubsets} 类似
     */
    public static boolean makesquare(int[] nums) {
        if (nums.length < 4) {
            return false;
        }
        int total = Arrays.stream(nums).sum();
        return total % 4 == 0 && dfs(nums, 4, total / 4, 0, 0, new boolean[nums.length]);
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
