package com.wz.array;

/**
 * You are given an integer array nums (0-indexed). In one operation, you can choose an element of the array and increment it by 1.
 * For example, if nums = [1,2,3], you can choose to increment nums[1] to make nums = [1,3,3].
 * Return the minimum number of operations needed to make nums strictly increasing.
 * An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1. An array of length 1 is trivially strictly increasing.
 *
 * Example 1:
 * Input: nums = [1,1,1]
 * Output: 3
 * Explanation: You can do the following operations:
 * 1) Increment nums[2], so nums becomes [1,1,2].
 * 2) Increment nums[1], so nums becomes [1,2,2].
 * 3) Increment nums[2], so nums becomes [1,2,3].
 *
 * Example 2:
 * Input: nums = [1,5,2,4,1]
 * Output: 14
 *
 * Constraints:
 * 1. 1 <= nums.length <= 5000
 * 2. 1 <= nums[i] <= 10^4
 */
public class MinimumOperationsToMakeTheArrayIncreasing {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 1, 1}));
        System.out.println(minOperations(new int[]{1, 5, 2, 4, 1}));
    }

    /**
     * 遍历数组，若当前元素大于前一个则继续，否则将当前元素更新为 前一个元素 + 1
     */
    public static int minOperations(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                count += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return count;
    }
}
