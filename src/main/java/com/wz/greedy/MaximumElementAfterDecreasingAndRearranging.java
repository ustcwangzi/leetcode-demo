package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given an array of positive integers arr. Perform some operations (possibly none) on arr so that it satisfies these conditions:
 * 1. The value of the first element in arr must be 1.
 * 2. The absolute difference between any 2 adjacent elements must be less than or equal to 1.
 *    In other words, abs(arr[i] - arr[i - 1]) <= 1 for each i where 1 <= i < arr.length (0-indexed). abs(x) is the absolute value of x.
 * There are 2 types of operations that you can perform any number of times:
 * 1. Decrease the value of any element of arr to a smaller positive integer.
 * 2. Rearrange the elements of arr to be in any order.
 * Return the maximum possible value of an element in arr after performing the operations to satisfy the conditions.
 *
 * Example 1:
 * Input: arr = [2,2,1,2,1]
 * Output: 2
 * Explanation:
 * We can satisfy the conditions by rearranging arr so it becomes [1,2,2,2,1].
 * The largest element in arr is 2.
 *
 * Example 2:
 * Input: arr = [100,1,1000]
 * Output: 3
 * Explanation:
 * One possible way to satisfy the conditions is by doing the following:
 * 1. Rearrange arr so it becomes [1,100,1000].
 * 2. Decrease the value of the second element to 2.
 * 3. Decrease the value of the third element to 3.
 * Now arr = [1,2,3], which satisfies the conditions.
 * The largest element in arr is 3.
 *
 * Constraints:
 * 1. 1 <= arr.length <= 10^5
 * 2. 1 <= arr[i] <= 10^9
 */
public class MaximumElementAfterDecreasingAndRearranging {
    public static void main(String[] args) {
        System.out.println(maximumElementAfterDecrementingAndRearranging(new int[]{2, 2, 1, 2, 1}));
    }

    /**
     * 每次操作只能使元素变小，为获得可能的最大值，应使所有元素都尽可能大
     * 根据题意第一个元素必须为 1，可将数组排序，再依次确保相邻元素的差值不超过 1，此时最后一个元素结果
     */
    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.parallelSort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }

        return arr[arr.length - 1];
    }
}
