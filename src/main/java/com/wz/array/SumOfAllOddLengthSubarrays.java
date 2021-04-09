package com.wz.array;

/**
 * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
 * A subarray is a contiguous subsequence of the array. Return the sum of all odd-length subarrays of arr.
 *
 * Example 1:
 * Input: arr = [1,4,2,5,3]
 * Output: 58
 * Explanation: The odd-length subarrays of arr and their sums are:
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 *
 * Constraints:
 * 1. 1 <= arr.length <= 100
 * 2. 1 <= arr[i] <= 1000
 */
public class SumOfAllOddLengthSubarrays {
    public static void main(String[] args) {
        System.out.println(sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
    }

    /**
     * 前缀和 + 滑动窗口
     * 先计算每个位置的前缀和 preSum[i+1] 代表 arr[0...i] 之和，然后使用滑动窗口 i、j 表示当前窗口
     * 如果长度为奇数，则将 arr[i...j] 的元素之和累加到结果中，即 preSum[j+1] - preSum[i]
     */
    public static int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if ((j - i + 1) % 2 == 0) {
                    continue;
                }
                result += preSum[j + 1] - preSum[i];
            }
        }
        return result;
    }
}
