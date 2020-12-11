package com.wz.dynamicprogramming;

/**
 * Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 * Return the largest sum of the given array after partitioning.
 *
 * Example 1:
 * Input: arr = [1,15,7,9,2,5,10], k = 3
 * Output: 84
 * Explanation: arr becomes [15,15,15,9,10,10,10]
 *
 * Example 2:
 * Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * Output: 83
 *
 * Constraints:
 * 1. 1 <= arr.length <= 500
 * 2. 0 <= arr[i] <= 109
 * 3. 1 <= k <= arr.length
 */
public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        System.out.println(maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3));
    }

    /**
     * dp[i] 表示前 i 个元素可以获取到的最大值
     * 对于每一个位置 j，往前遍历 1～k 个元素来求最优值
     * 假设 [i-j,i] 这一段的最大值 curMax，那么 dp[i] = max{dp[i],(i>=K ? dp[i-j] : 0) + curMax*j}
     * curMax 可以通过遍历 j 的时候更新
     */
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int curMax = 0;
            for (int j = 1; j <= k && i - j + 1 >= 0; j++) {
                curMax = Math.max(curMax, arr[i - j + 1]);
                dp[i] = Math.max(dp[i], (i >= k ? dp[i - j] : 0) + curMax * j);
            }
        }
        return dp[n - 1];
    }
}
