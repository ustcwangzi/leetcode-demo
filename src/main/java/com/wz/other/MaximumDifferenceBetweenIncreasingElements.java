package com.wz.other;

/**
 * Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
 * Return the maximum difference. If no such i and j exists, return -1.
 *
 * Example 1:
 * Input: nums = [7,1,5,4]
 * Output: 4
 * Explanation:
 * The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
 * Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
 *
 * Example 2:
 * Input: nums = [9,4,3,2]
 * Output: -1
 * Explanation:
 * There is no i and j such that i < j and nums[i] < nums[j].
 *
 * Example 3:
 * Input: nums = [1,5,2,10]
 * Output: 9
 * Explanation:
 * The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 2 <= n <= 1000
 * 3. 1 <= nums[i] <= 10^9
 */
public class MaximumDifferenceBetweenIncreasingElements {
    public static void main(String[] args) {
        System.out.println(maximumDifference(new int[]{7, 1, 5, 4}));
        System.out.println(maximumDifference(new int[]{9, 4, 3, 2}));
        System.out.println(maximumDifference(new int[]{1, 5, 2, 10}));
    }

    /**
     * 遍历数组，使用 min 记录已遍历的最小值，result 记录最大差值
     * 由于结果只能是后面的数减去前面的数，因此直接在遍历中更新 result 和 min 即可
     */
    public static int maximumDifference(int[] nums) {
        int result = 0, min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, nums[i] - min);
            min = Math.min(min, nums[i]);
        }
        return result == 0 ? -1 : result;
    }
}
