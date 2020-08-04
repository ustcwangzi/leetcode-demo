package com.wz.lists;

/**
 * Given an array of integers nums, you start with an initial positive value startValue.
 * In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
 * Return the minimum positive value of startValue such that the step by step sum is never less than 1.
 *
 * Example 1:
 * Input: nums = [-3,2,-3,4,2]
 * Output: 5
 * Explanation: If you choose startValue = 4, in the third iteration your step by step sum is less than 1.
 *                 step by step sum
 *                 startValue = 4 | startValue = 5 | nums
 *                   (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
 *                   (1 +2 ) = 3  | (2 +2 ) = 4    |   2
 *                   (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
 *                   (0 +4 ) = 4  | (1 +4 ) = 5    |   4
 *                   (4 +2 ) = 6  | (5 +2 ) = 7    |   2
 *
 * Example 2:
 * Input: nums = [1,2]
 * Output: 1
 * Explanation: Minimum start value should be positive.
 *
 * Example 3:
 * Input: nums = [1,-2,-3]
 * Output: 5
 */
public class MinimumValueToGetPositiveStepByStepSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-3, 2, -3, 4, 2};
        System.out.println(minStartValue(nums));

        nums = new int[]{1, 2};
        System.out.println(minStartValue(nums));

        nums = new int[]{1, -2, -3};
        System.out.println(minStartValue(nums));
    }

    /**
     * 选择一个初始值，保证每一步相加都大于0
     * 直接计算前缀和 preSum，其中的最小值为 minSum。若 minSum >= 0，则初始值选择1即可，否则初始值为 |minSum| + 1
     */
    public static int minStartValue(int[] nums) {
        int preSum = 0, minSum = Integer.MAX_VALUE;
        for (int num : nums) {
            preSum += num;
            minSum = Math.min(minSum, preSum);
        }

        return minSum >= 0 ? 1 : Math.abs(minSum) + 1;
    }
}
