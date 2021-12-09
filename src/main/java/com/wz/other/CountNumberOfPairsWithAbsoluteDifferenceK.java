package com.wz.other;

/**
 * Given an integer array nums and an integer k, return the number of pairs (i, j) where i < j such that |nums[i] - nums[j]| == k.
 * The value of |x| is defined as:
 * 1. x if x >= 0.
 * 2. -x if x < 0.
 *
 * Example 1:
 * Input: nums = [1,2,2,1], k = 1
 * Output: 4
 * Explanation: The pairs with an absolute difference of 1 are:
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 *
 * Example 2:
 * Input: nums = [3,2,1,5,4], k = 2
 * Output: 3
 * Explanation: The pairs with an absolute difference of 2 are:
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 200
 * 2. 1 <= nums[i] <= 100
 * 3. 1 <= k <= 99
 */
public class CountNumberOfPairsWithAbsoluteDifferenceK {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 4};
        System.out.println(countKDifference(nums, 2));
        System.out.println(countKDifference2(nums, 2));
    }

    /**
     * 直接双层遍历
     */
    public static int countKDifference(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 使用 freq[] 记录每个数字出现次数，然后寻找 freq[num] 与 freq[num+k]
     */
    public static int countKDifference2(int[] nums, int k) {
        int[] freq = new int[101];
        for (int num : nums) {
            freq[num]++;
        }

        int count = 0;
        for (int num = 1; num <= 100 - k; num++) {
            count += freq[num] * freq[num + k];
        }
        return count;
    }
}
