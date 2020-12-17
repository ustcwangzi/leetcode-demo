package com.wz.dynamicprogramming;

/**
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 *
 * Example 1:
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 *
 * Example 2:
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 *
 * Example 3:
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 *
 * Constraints:
 * 1. 1 <= nums.length <= 4 * 10^4
 * 2. 1 <= nums[i] <= 10^4
 */
public class GreatestSumDivisibleByThree {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree1(nums));
        System.out.println(maxSumDivThree2(nums));
        System.out.println(maxSumDivThree3(nums));
    }

    /**
     * dp[i][k] 表示截止到 nums[i] 被 3 整除余数为k的最大和，需要考虑 k=0, k=1, k=2 这三种情况：
     * dp[i][*] = max{dp[i-1][*], dp[i-1][*] + nums[i]}  (* 取值为 0,1,2)
     */
    public static int maxSumDivThree1(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][3];
        dp[0][nums[0] % 3] = nums[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = 0; j < 3; j++) {
                int curSum = dp[i - 1][j] + nums[i];
                dp[i][curSum % 3] = Math.max(dp[i][curSum % 3], curSum);
            }
        }
        return dp[n - 1][0];
    }

    /**
     * dp[i] 表示截止到 nums[i] 被 3整除余数为 k 的最大和
     * dp[(i+num) % 3] = max{dp[(i+num) % 3], dp[i] + num}
     */
    public static int maxSumDivThree2(int[] nums) {
        int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int num : nums) {
            int[] tmp = new int[3];
            for (int i = 0; i < 3; ++i) {
                tmp[(i + num) % 3] = Math.max(dp[(i + num) % 3], dp[i] + num);
            }
            dp = tmp;
        }
        return dp[0];
    }

    /**
     * 最后数组和只有三种情况
     * 除以3余0，直接返回
     * 除以3余1，那么要么减少一个除以3余1的数字、或者减少两个除以3余2的数字
     * 除以3余2，那么要么减少一个除以3余2的数字、或者减少两个除以3余1的数字
     */
    public static int maxSumDivThree3(int[] nums) {
        int result = 0, leftOne = 40000, leftTwo = 40000;
        for (int num : nums) {
            result += num;
            if (num % 3 == 1) {
                leftTwo = Math.min(leftTwo, leftOne + num);
                leftOne = Math.min(leftOne, num);
            }
            if (num % 3 == 2) {
                leftOne = Math.min(leftOne, leftTwo + num);
                leftTwo = Math.min(leftTwo, num);
            }
        }
        if (result % 3 == 0) {
            return result;
        }
        if (result % 3 == 1) {
            return result - leftOne;
        }
        return result - leftTwo;
    }
}
