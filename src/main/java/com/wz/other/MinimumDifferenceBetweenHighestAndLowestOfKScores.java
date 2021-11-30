package com.wz.other;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums, where nums[i] represents the score of the ith student. You are also given an integer k.
 * Pick the scores of any k students from the array so that the difference between the highest and the lowest of the k scores is minimized.
 * Return the minimum possible difference.
 *
 * Example 1:
 * Input: nums = [90], k = 1
 * Output: 0
 * Explanation: There is one way to pick score(s) of one student:
 * - [90]. The difference between the highest and lowest score is 90 - 90 = 0.
 * The minimum possible difference is 0.
 *
 * Example 2:
 * Input: nums = [9,4,1,7], k = 2
 * Output: 2
 * Explanation: There are six ways to pick score(s) of two students:
 * - [9,4,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
 * - [9,4,1,7]. The difference between the highest and lowest score is 9 - 1 = 8.
 * - [9,4,1,7]. The difference between the highest and lowest score is 9 - 7 = 2.
 * - [9,4,1,7]. The difference between the highest and lowest score is 4 - 1 = 3.
 * - [9,4,1,7]. The difference between the highest and lowest score is 7 - 4 = 3.
 * - [9,4,1,7]. The difference between the highest and lowest score is 7 - 1 = 6.
 * The minimum possible difference is 2.
 *
 * Constraints:
 * 1. 1 <= k <= nums.length <= 1000
 * 2. 0 <= nums[i] <= 10^5
 */
public class MinimumDifferenceBetweenHighestAndLowestOfKScores {
    public static void main(String[] args) {
        System.out.println(minimumDifference(new int[]{9, 4, 1, 7}, 2));
    }

    /**
     * 排序 + 滑动窗口
     * 排序后，左指针指向最小的元素，右指针指向最大的元素，所以只要左右指针的距离等于 k，就可以扫描一遍数组得到所有 k 个数字中的最小差值
     */
    public static int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        Arrays.parallelSort(nums);
        for (int i = k - 1; i < nums.length; i++) {
            result = Math.min(result, nums[i] - nums[i - k + 1]);
        }
        return result;
    }
}
