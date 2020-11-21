package com.wz.dynamicprogramming;

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 * nums = [1, 2, 3]
 * target = 4
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 */
public class CombinationSumIV {
    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
    }

    /**
     * 动态规划
     * dp[i] 表示目标为 i 的结果个数
     * dp[0] = 1 表示不选择任何数字，然后从 1 遍历到 target，对于每个 i，遍历 nums 数组，如果 i>=num, dp[i] += dp[i-num]
     * 比如说对于 [1,2,3] 4，当计算 dp[3] 时，可以拆分为 1+dp[2]、2+dp[1]、3+dp[0]，把所有的情况加起来就是组成 3 的所有情况
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
