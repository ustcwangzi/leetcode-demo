package com.wz.array;

import java.util.Arrays;

/**
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer,
 * say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 *
 * Example 1:
 * Input: [1,4,3,2]
 * Output: 4
 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 */
public class ArrayPartitionI {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 4, 3, 2};
        System.out.println(arrayPairSum(nums));
    }

    /**
     * 要在两个数中取最小的值，应该尽量找相邻的两个数一组，这样才不会浪费一个大的数值
     * 所以将数组排序，找到两个中间大的那一个
     */
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);

        int result = 0;
        for (int i = 0; i < nums.length; i += 2) {
            result += nums[i];
        }

        return result;
    }
}
