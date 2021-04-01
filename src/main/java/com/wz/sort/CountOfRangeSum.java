package com.wz.sort;

import java.util.TreeMap;

/**
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 *
 * Example 1:
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^4
 * 2. -2^31 <= nums[i] <= 2^31 - 1
 * 3. -3 * 10^4 <= lower <= upper <= 3 * 10^4
 */
public class CountOfRangeSum {
    public static void main(String[] args) {
        System.out.println(countRangeSum(new int[]{-2, 5, -1}, -2, 2));
    }

    /**
     * 寻找 rangeSum 在 [lower, upper] 之间的个数，即
     * lower <= sum[i]-sum[j] <= upper, i > j
     * 也就是
     * sum[i]-upper <=  sum[j] <= sum[i]-lower, i > j
     * 问题转化为求落在 [sum[i]-upper, sum[i]-lower] 的 sum[j] 个数, i = 0....n, j < i
     * TreeMap 的 key 是 preSum, value 是相应个数，使用 TreeMap.subMap，求得落在ø [sum[i]-upper, sum[i]-lower] 的 sum[j] 个数
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        TreeMap<Long, Integer> map = new TreeMap<>();
        long sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            if (sum >= lower && sum <= upper) {
                count++;
            }

            // 位于 [sum[i]-upper, sum[i]-lower] 直接的 sum[j] 个数
            count += map.subMap(sum - upper, true, sum - lower, true).values().stream().mapToInt(Integer::valueOf).sum();
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
