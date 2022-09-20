package com.wz.twopointer;

/**
 * You are given an array of integers nums (0-indexed) and an integer k.
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
 * Return the maximum possible score of a good subarray.
 *
 * Example 1:
 * Input: nums = [1,4,3,7,4,5], k = 3
 * Output: 15
 * Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
 *
 * Example 2:
 * Input: nums = [5,5,4,5,4,1,1,1], k = 0
 * Output: 20
 * Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 2 * 10^4
 * 3. 0 <= k < nums.length
 */
public class MaximumScoreOfGoodSubarray {
    public static void main(String[] args) {
        System.out.println(maximumScore(new int[]{1, 4, 3, 7, 4, 5}, 3));
        System.out.println(maximumScore(new int[]{5, 5, 4, 5, 4, 1, 1, 1}, 0));
    }

    /**
     * 满足题意的数组是从索引 k 向两侧延伸的数组，其分数取决于区间内的最小值和区间长度
     * 因此可在数组中索引为 k 处向左右进行延伸，同时记录结果
     * 然后再选取左、右区间中下一个较大的最小值，并再次计算分数
     */
    public static int maximumScore(int[] nums, int k) {
        int n = nums.length, left = k, right = k, min = nums[k], result = nums[k];
        while (true) {
            while (right + 1 < n && nums[right + 1] >= min) {
                right++;
            }
            while (left - 1 >= 0 && nums[left - 1] >= min) {
                left--;
            }

            result = Math.max(result, min * (right - left + 1));

            if (left == 0 && right == n - 1) {
                break;
            }

            if (left == 0) {
                min = nums[right + 1];
            } else if (right == n - 1) {
                min = nums[left - 1];
            } else {
                min = Math.max(nums[left - 1], nums[right + 1]);
            }
        }
        return result;
    }
}
