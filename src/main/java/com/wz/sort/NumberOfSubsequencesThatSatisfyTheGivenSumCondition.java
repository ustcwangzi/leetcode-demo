package com.wz.sort;

import java.util.Arrays;

/**
 * Given an array of integers nums and an integer target.
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it
 * is less or equal to target. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition.
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 *
 * Example 2:
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^6
 * 3. 1 <= target <= 10^6
 */
public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    public static void main(String[] args) {
        System.out.println(numSubseq(new int[]{3, 5, 6, 7}, 9));
        System.out.println(numSubseq(new int[]{3, 3, 6, 8}, 10));
    }

    /**
     * 滑动窗口
     * 对数组进行排序，先选择最小值，然后寻找满足条件的最大值，确定好最小值和最大值之后，子序列个数为：2^(j-i)
     */
    public static int numSubseq(int[] nums, int target) {
        Arrays.parallelSort(nums);
        int n = nums.length, result = 0, mod = 1000000007;

        int[] pow = new int[nums.length];
        pow[0] = 1;
        // 为方面下面计算，这里先计算 2^i
        for (int i = 1; i < nums.length; ++i) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }

        int left = 0, right = n - 1;
        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                result = (result + pow[right - left]) % mod;
                left++;
            }
        }
        return result;
    }
}
