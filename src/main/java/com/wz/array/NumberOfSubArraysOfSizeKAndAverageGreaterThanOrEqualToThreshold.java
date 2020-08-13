package com.wz.array;

/**
 * Given an array of integers arr and two integers k and threshold.
 * Return the number of sub-arrays of size k and average greater than or equal to threshold.
 *
 * Example 1:
 * Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * Output: 3
 * Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively.
 *              All other sub-arrays of size 3 have averages less than 4 (the threshold).
 *
 * Example 2:
 * Input: arr = [1,1,1,1,1], k = 1, threshold = 0
 * Output: 5
 *
 * Example 3:
 * Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * Output: 6
 * Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
 *
 * Example 4:
 * Input: arr = [7,7,7,7,7,7,7], k = 7, threshold = 7
 * Output: 1
 *
 * Example 5:
 * Input: arr = [4,4,4,4], k = 4, threshold = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^4
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10^4
 */
public class NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(numOfSubarrays(arr, 3, 4));

        arr = new int[]{1, 1, 1, 1, 1};
        System.out.println(numOfSubarrays(arr, 1, 0));

        arr = new int[]{11, 13, 17, 23, 29, 31, 7, 5, 2, 3};
        System.out.println(numOfSubarrays(arr, 3, 5));

        arr = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println(numOfSubarrays(arr, 7, 7));
    }

    /**
     * 长度为 k 的子数组元素之和的平均值 >= threshold
     * 动态规划
     * dp[i] 代表 arr[k-i+1 ... i] 这 k 个元素之和，然后遍历 dp 求出满足 threshold * k 的元素个数即可
     */
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        if (n == k) {
            return 1;
        }

        int[] dp = new int[n];
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] += arr[i] + dp[i - 1];
            // 需要减去前面的值，保持长度为 k
            if (i >= k) {
                dp[i] -= arr[i - k];
            }
        }

        int result = 0, target = threshold * k;
        for (int i = k - 1; i < n; i++) {
            if (dp[i] >= target) {
                result++;
            }
        }
        return result;
    }
}
