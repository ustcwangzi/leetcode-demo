package com.wz.array;

import java.util.Arrays;

/**
 * Given two integer arrays of equal length target and arr.
 *
 * In one step, you can select any non-empty sub-array of arr and reverse it. You are allowed to make any number of steps.
 *
 * Return True if you can make arr equal to target, or False otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: target = [1,2,3,4], arr = [2,4,1,3]
 * Output: true
 * Explanation: You can follow the next steps to convert arr to target:
 * 1- Reverse sub-array [2,4,1], arr becomes [1,4,2,3]
 * 2- Reverse sub-array [4,2], arr becomes [1,2,4,3]
 * 3- Reverse sub-array [4,3], arr becomes [1,2,3,4]
 * There are multiple ways to convert arr to target, this is not the only way to do so.
 *
 * Example 2:
 * Input: target = [3,7,9], arr = [3,7,11]
 * Output: false
 * Explanation: arr doesn't have value 9 and it can never be converted to target.
 */
public class MakeTwoArraysEqualByReversingSubarrays {
    public static void main(String[] args) {
        int[] target = new int[]{1, 2, 3, 4};
        int[] arr = new int[]{2, 4, 1, 3};
        System.out.println(canBeEqual(target, arr));

        target = new int[]{3, 7, 9};
        arr = new int[]{3, 7, 11};
        System.out.println(canBeEqual(target, arr));
    }

    /**
     * 因为不限制翻转次数，因此只要两个数组包含的元素相同，即可通过翻转达到相等
     * 直接排序后判断两个数组对应位置元素是否相等即可
     */
    public static boolean canBeEqual(int[] target, int[] arr) {
        Arrays.parallelSort(target);
        Arrays.parallelSort(arr);
        return Arrays.equals(target, arr);
    }
}
