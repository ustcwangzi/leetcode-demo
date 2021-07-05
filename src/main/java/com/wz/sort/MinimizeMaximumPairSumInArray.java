package com.wz.sort;

import java.util.Arrays;

/**
 * The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
 * For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
 * Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
 * Each element of nums is in exactly one pair, and
 * The maximum pair sum is minimized.
 * Return the minimized maximum pair sum after optimally pairing up the elements.
 *
 * Example 1:
 * Input: nums = [3,5,2,3]
 * Output: 7
 * Explanation: The elements can be paired up into pairs (3,3) and (5,2).
 * The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.
 *
 * Example 2:
 * Input: nums = [3,5,4,2,4,6]
 * Output: 8
 * Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
 * The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 2 <= n <= 10^5
 * 3. n is even.
 * 4. 1 <= nums[i] <= 10^5
 */
public class MinimizeMaximumPairSumInArray {
    public static void main(String[] args) {
        System.out.println(minPairSum(new int[]{3, 5, 2, 3}));
        System.out.println(minPairSum(new int[]{3, 5, 4, 2, 4, 6}));
    }

    /**
     * 排序 + 双指针
     * 想要和最小，一定是每次选择数组中的最大值和最小值进行相加，因此先对数组进行排序，然后每次将当前首尾元素相加，更新结果
     */
    public static int minPairSum(int[] nums) {
        Arrays.parallelSort(nums);
        int i = 0, j = nums.length - 1, result = Integer.MIN_VALUE;
        while (i < j) {
            result = Math.max(result, nums[i++] + nums[j--]);
        }
        return result;
    }
}
