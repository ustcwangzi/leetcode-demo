package com.wz.lists;

import java.util.Arrays;

/**
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 *
 * Example 1:
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 *
 * Example 2:
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 */
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 3, 2, 4};
        System.out.println(minDifference(nums));

        nums = new int[]{1, 5, 0, 10, 14};
        System.out.println(minDifference(nums));
    }

    /**
     * 如果数字的个数小于等于 4 个，则答案为 0
     * 将数组从小到大排序，此时数组为 0, 1, 2, 3, ... , n-4, n-3, n-2, n-1
     * 有四种修改方法
     * 1. 修改最小的三个数字，即将 0,1,2 变成 3，此时差值为 nums[n-2] - nums[2]
     * 2. 修改最小的两个数字和最大的数字，即将 0,1 变成 2，n-1 变成 n-2，此时差值为 nums[n-2] - nums[2]
     * 3. 修改最小的数字和最大的两个数字，即将 0 变成 1，n-2,n-1 变成 n-3，此时差值为 nums[n-3] - nums[1]
     * 4. 修改最大的三个数字，即将 n-3,n-2,n-1 变成 n-4，此时差值为 nums[n-4] - nums[0]
     */
    public static int minDifference(int[] nums) {
        int n = nums.length;
        if (n <= 4) {
            return 0;
        }

        Arrays.parallelSort(nums);
        return Math.min(Math.min(nums[n - 1] - nums[3], nums[n - 2] - nums[2]),
                Math.min(nums[n - 3] - nums[1], nums[n - 4] - nums[0]));
    }
}
