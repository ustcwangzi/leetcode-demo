package com.wz.math;

import java.util.Arrays;

/**
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal,
 * where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 * You may assume the array's length is at most 10,000.
 *
 * Example:
 * Input:
 * [1,2,3]
 * Output:
 * 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
public class MinimumMovesToEqualArrayElementsII {
    public static void main(String[] args) {
        System.out.println(minMoves2(new int[]{1, 2, 3}));
    }

    /**
     * 每次可以对任意一个数字加1或者减1，让用最少的次数让数组所有值相等
     * 实际上最后相等的数字就是数组的最中间的那个数字，那么给数组排序后，定位到中间的数字，然后累加数组中每个元素与其差的绝对值即可
     */
    public static int minMoves2(int[] nums) {
        Arrays.parallelSort(nums);
        int result = 0, mid = nums[nums.length / 2];
        for (int num : nums) {
            result += Math.abs(num - mid);
        }
        return result;
    }
}
