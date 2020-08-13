package com.wz.array;

import java.util.Arrays;

/**
 * Given the array of integers nums, you will choose two different indices i and j of that array.
 * Return the maximum value of (nums[i]-1)*(nums[j]-1).
 *
 * Example 1:
 * Input: nums = [3,4,5,2]
 * Output: 12
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0),
 *              you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12.
 *
 * Example 2:
 * Input: nums = [1,5,4,5]
 * Output: 16
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
 *
 * Constraints:
 * 2 <= nums.length <= 500
 * 1 <= nums[i] <= 10^3
 */
public class MaximumProductOfTwoElementsInArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 5, 2};
        System.out.println(maxProduct(nums));

        nums = new int[]{1, 5, 4, 5};
        System.out.println(maxProduct(nums));
    }

    /**
     * 排序后最后的两个数分别减1后相乘即可
     */
    public static int maxProduct(int[] nums) {
        Arrays.parallelSort(nums);
        int n = nums.length;
        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }
}
