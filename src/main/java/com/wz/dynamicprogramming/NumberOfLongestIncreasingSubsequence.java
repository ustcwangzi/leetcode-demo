package com.wz.dynamicprogramming;

/**
 * Given an integer array nums, return the number of longest increasing subsequences.
 * Notice that the sequence has to be strictly increasing.
 *
 * Example 1:
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 2000
 * 2. -10^6 <= nums[i] <= 10^6
 */
public class NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1, 2, 5, 4, 7}));
    }

    /**
     * 动态规划
     * len[i] 表示以 nums[i] 为结尾的递增序列的长度，count[i] 表示以 nums[i] 为结尾的递增序列的个数，初始化都赋值为1
     * 遍历数组，对于 nums[i]，再遍历其之前的所有数字 nums[j]，当 nums[i] 小于等于 nums[j] 时，不做任何处理，因为不是递增序列。
     * 反之，则判断 len[i] 和 len[j] 的关系，如果 len[i] 等于 len[j] + 1，说明 nums[i] 可以加在以 nums[j] 结尾的递增序列后面，
     * 并且以 nums[j] 结尾的递增序列个数可以直接加到以 nums[i] 结尾的递增序列个数上，即 count[i] += count[j]
     * 如果 len[i] 小于 len[j] + 1，说明找到了更长的递增序列，那么此时将 len[i] 更新为 len[j]+1，并且原本的递增序列都不能用了，
     * 直接用 count[j] 来代替 count[i]。
     * 在更新完 len[i] 和 count[i] 之后，要更新 maxLength 和结果 result
     * 如果 maxLength 等于 len[i]，则把 count[i] 加到结果 result 之中；
     * 如果 maxLength 小于 len[i]，则更新 maxLength 为 len[i]，更新结果 result 为 count[i]
     */
    public static int findNumberOfLIS(int[] nums) {
        int result = 0, maxLength = 0, n = nums.length;
        int[] len = new int[n], count = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) {
                    continue;
                }
                if (len[i] == len[j] + 1) {
                    count[i] += count[j];
                } else if (len[i] < len[j] + 1) {
                    len[i] = len[j] + 1;
                    count[i] = count[j];
                }
            }

            if (maxLength == len[i]) {
                result += count[i];
            } else if (maxLength < len[i]) {
                maxLength = len[i];
                result = count[i];
            }
        }

        return result;
    }
}
