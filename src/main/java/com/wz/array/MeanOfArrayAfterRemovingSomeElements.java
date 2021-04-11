package com.wz.array;

import java.util.Arrays;

/**
 * Given an integer array arr, return the mean of the remaining integers after removing the smallest 5% and the largest 5% of the elements.
 * Answers within 10-5 of the actual answer will be considered accepted.
 *
 * Example 1:
 * Input: arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
 * Output: 2.00000
 * Explanation: After erasing the minimum and the maximum values of this array, all elements are equal to 2, so the mean is 2.
 *
 * Example 2:
 * Input: arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
 * Output: 4.00000
 *
 * Constraints:
 * 1. 20 <= arr.length <= 1000
 * 2. arr.length is a multiple of 20.
 * 3. 0 <= arr[i] <= 10^5
 */
public class MeanOfArrayAfterRemovingSomeElements {
    public static void main(String[] args) {
        System.out.println(trimMean(new int[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3}));
    }

    /**
     * 移除最大、最小的 5% 元素后，求剩余元素的平均数
     */
    public static double trimMean(int[] arr) {
        Arrays.parallelSort(arr);
        int n = arr.length, removeLen = (int) (n * 0.05);
        double sum = 0;
        for (int i = removeLen; i < n - removeLen; i++) {
            sum += arr[i];
        }
        return sum / (n - 2 * removeLen);
    }
}
