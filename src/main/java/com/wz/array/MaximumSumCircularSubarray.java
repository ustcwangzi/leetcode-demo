package com.wz.array;

/**
 * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
 * Here, a circular array means the end of the array connects to the beginning of the array.
 * (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
 * Also, a subarray may only include each element of the fixed buffer A at most once.
 * (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
 *
 * Example 1:
 * Input: [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 *
 * Example 2:
 * Input: [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 *
 * Example 3:
 * Input: [3,-1,2,-1]
 * Output: 4
 * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
 */
public class MaximumSumCircularSubarray {
    public static void main(String[] args) {
        int[] A = new int[]{3,-1,2,-1};
        System.out.println(maxSubarraySumCircular(A));
    }

    /**
     * @see ../../../../resource/MaximumSumCircularSubarray.jpg
     * 是对 {@link MaximumSubarray} 的扩展
     * 如果数组不是环形的那么答案就是最大子数组和，如果是环形的那么数组除去答案的那一部分仍然是连续的，
     * 这样答案就是 max{最大子数组和，sum - 最小子数组和}，注意这里如果数组全为负数，应该返回最大子数组和而不是0
     */
    public static int maxSubarraySumCircular(int[] nums) {
        int allSum = 0, maxSum = nums[0], curMax = 0, minSum = nums[0], curMin = 0;
        for (int num : nums) {
            curMax = Math.max(curMax + num, num);
            // 最大子数组之和
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + num, num);
            // 最小子数组之和
            minSum = Math.min(minSum, curMin);
            allSum += num;
        }
        return maxSum > 0 ? Math.max(maxSum, allSum - minSum) : maxSum;
    }
}
