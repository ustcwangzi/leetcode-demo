package com.wz.sort;

import java.util.Arrays;

/**
 * You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.
 * Return the sum of the k integers appended to nums.
 *
 * Example 1:
 * Input: nums = [1,4,25,10,25], k = 2
 * Output: 5
 * Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
 * The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
 * The sum of the two integers appended is 2 + 3 = 5, so we return 5.
 *
 * Example 2:
 * Input: nums = [5,6], k = 6
 * Output: 25
 * Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
 * The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum.
 * The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^9
 * 3. 1 <= k <= 10^8
 */
public class AppendKIntegersWithMinimalSum {
    public static void main(String[] args) {
        System.out.println(minimalKSum(new int[]{5, 6}, 6));
    }

    /**
     * 假设前 k 个数都不存在，那么答案直接就是 (1+k)*k/2，k 个数中每有一个数存在于 nums，k 就需要往后移动一位
     * 即问题转换为：将前 k 个数求和，再减去其中存在于 nums 的数字之和即可
     * 只需要判断前 k 个数，因此先对 nums 进行排序，然后遍历数组
     * - 若 num <= k，说明需要将 k 右移，同时将 num 累加到 sum 中
     * - 若 num > k，说明前 k 个数已经找到，直接终止即可
     * - 同时需要考虑重复元素，因为重复元素并不影响最终结果，即对于同一个 num，k 不需要右移两次
     */
    public static long minimalKSum(int[] nums, int k) {
        Arrays.parallelSort(nums);
        // 因为数组已经排序了，直接使用 pre 来过滤重复元素
        int pre = -1;
        long sum = 0;
        for (int num : nums) {
            if (pre == num) {
                continue;
            }
            // 已找到 k 个元素
            if (num > k) {
                break;
            }

            k++;
            sum += num;
            pre = num;
        }
        return (long) (k + 1) * k / 2 - sum;
    }
}
