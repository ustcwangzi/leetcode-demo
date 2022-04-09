package com.wz.other;

/**
 * You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).
 * The triangular sum of nums is the value of the only element present in nums after the following process terminates:
 * 1. Let nums comprise of n elements. If n == 1, end the process. Otherwise, create a new 0-indexed integer array newNums of length n - 1.
 * 2. For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10, where % denotes modulo operator.
 * 3. Replace the array nums with newNums.
 * 4. Repeat the entire process starting from step 1.
 * Return the triangular sum of nums.
 *
 * Example 1:
 * @link ../../../../resource/FindTriangularSumOfArray.jpg
 * Input: nums = [1,2,3,4,5]
 * Output: 8
 * Explanation:
 * The above diagram depicts the process from which we obtain the triangular sum of the array.
 *
 * Example 2:
 * Input: nums = [5]
 * Output: 5
 * Explanation:
 * Since there is only one element in nums, the triangular sum is the value of that element itself.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 0 <= nums[i] <= 9
 */
public class FindTriangularSumOfArray {
    public static void main(String[] args) {
        System.out.println(triangularSum(new int[]{1, 2, 3, 4, 5}));
        System.out.println(triangularSum(new int[]{5}));
    }

    /**
     * 从 n ～ 1 逐层计算，最后返回 nums[0] 即可
     */
    public static int triangularSum(int[] nums) {
        int n = nums.length;
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            n--;
        }
        return nums[0];
    }
}
