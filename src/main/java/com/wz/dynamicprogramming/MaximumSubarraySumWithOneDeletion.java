package com.wz.dynamicprogramming;

/**
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion.
 * In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least
 * one element left and the sum of the remaining elements is maximum possible.
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 * Example 1:
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 *
 * Example 2:
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 *
 * Constraints:
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 */
public class MaximumSubarraySumWithOneDeletion {
    public static void main(String[] args) {
        System.out.println(maximumSum(new int[]{1, -2, -2, 3}));
    }

    /**
     * f[i] 表示以 i 结尾、且没有删除过元素的最大子数组之和
     * g[i] 表示以 i 结尾、删除过元素的最大子数组之和
     * f[i] 有两种选择，一种是用之前的结果和当前元素累加，第二种是直接从当前元素重新开始
     * g[i] 也有两种选择，不删除当前元素，即从 g[i−1] 加上当前元素 转移，第二种是删除当前元素，即从 dp(i−1) 转移
     * 最终结果为 max{f(i), g(i)}
     */
    public static int maximumSum(int[] arr) {
        int n = arr.length, result = arr[0];
        int[] f = new int[n], g = new int[n];
        f[0] = arr[0];

        for (int i = 1; i < n; i++) {
            // 累加或不累加之前的结果
            f[i] = Math.max(f[i - 1] + arr[i], arr[i]);
            // 删除或不删除当前元素
            g[i] = Math.max(g[i - 1] + arr[i], f[i - 1]);
            result = Math.max(result, Math.max(f[i], g[i]));
        }
        return result;
    }
}
