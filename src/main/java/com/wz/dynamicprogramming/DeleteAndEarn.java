package com.wz.dynamicprogramming;

/**
 * Given an array nums of integers, you can perform operations on the array.
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points.
 * After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 *
 * Example 1:
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation:
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 *
 * Example 2:
 * Input: nums = [2, 2, 3, 3, 3, 4]
 * Output: 9
 * Explanation:
 * Delete 3 to earn 3 points, deleting both 2's and the 4.
 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 * 9 total points are earned.
 *
 * Note:
 * 1. The length of nums is at most 20000.
 * 2. Each element nums[i] is an integer in the range [1, 10000].
 */
public class DeleteAndEarn {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }

    /**
     * 对于每一个数字都有两个选择，拿或者不拿，如果拿了当前的数字，就不能拿之前的数字，那么当前的积分就是不拿前面的数字的积分加上当前数字之和
     * 先遍历原数组，将遇到的数字都累加到该数字在数组中的位置上，因为每个数组范围是 [1, 10000]，因此 dp 大小初始化为 10001
     * 然后遍历 dp 数组，当前 dp[i] 值就等于拿当前数字和不拿当前数字两者中的较大值，即 dp[i] = max{dp[i-1], dp[i-2] + dp[i]}
     */
    public static int deleteAndEarn(int[] nums) {
        int[] dp = new int[10001];
        for (int num : nums) {
            dp[num] += num;
        }
        for (int i = 2; i < 10001; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + dp[i]);
        }
        return dp[10000];
    }
}
