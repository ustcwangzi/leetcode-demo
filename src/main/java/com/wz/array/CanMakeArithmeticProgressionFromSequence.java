package com.wz.array;

import java.util.Arrays;

/**
 * Given an array of numbers arr. A sequence of numbers is called an arithmetic progression
 * if the difference between any two consecutive elements is the same.
 * Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.
 *
 * Example 1:
 * Input: arr = [3,5,1]
 * Output: true
 * Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
 *
 * Example 2:
 * Input: arr = [1,2,4]
 * Output: false
 * Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
 */
public class CanMakeArithmeticProgressionFromSequence {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 1};
        System.out.println(canMakeArithmeticProgression(arr));

        arr = new int[]{1, 2, 4};
        System.out.println(canMakeArithmeticProgression(arr));
    }

    /**
     * 排序后比较相邻元素的差值即可
     */
    public static boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.parallelSort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }
}
