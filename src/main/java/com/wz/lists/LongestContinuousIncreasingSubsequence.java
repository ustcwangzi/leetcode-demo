package com.wz.lists;

/**
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 *
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 */
public class LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 4, 7, 8, 9};
        System.out.println(findLengthOfLCIS(nums));

        nums = new int[]{2, 2, 2};
        System.out.println(findLengthOfLCIS(nums));
    }

    /**
     * 直接遍历，和前一个元素进行比较即可
     */
    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int result = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            result = Math.max(result, count);
        }

        return result;
    }
}
