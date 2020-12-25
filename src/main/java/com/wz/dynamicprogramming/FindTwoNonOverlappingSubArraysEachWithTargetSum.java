package com.wz.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers arr and an integer target.
 * You have to find two non-overlapping sub-arrays of arr each with sum equal target.
 * There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 *
 * Example 1:
 * Input: arr = [3,2,2,4,3], target = 3
 * Output: 2
 * Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.
 *
 * Example 2:
 * Input: arr = [7,3,4,7], target = 7
 * Output: 2
 * Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]),
 * but we will choose the first and third sub-arrays as the sum of their lengths is 2.
 *
 * Example 3:
 * Input: arr = [3,1,1,1,5,1,2,1], target = 3
 * Output: 3
 * Explanation: Note that sub-arrays [1,2] and [2,1] cannot be an answer because they overlap.
 *
 * Constraints:
 * 1. 1 <= arr.length <= 10^5
 * 2. 1 <= arr[i] <= 1000
 * 3. 1 <= target <= 10^8
 */
public class FindTwoNonOverlappingSubArraysEachWithTargetSum {
    public static void main(String[] args) {
        System.out.println(minSumOfLengths(new int[]{7, 3, 4, 7}, 7));
    }

    /**
     * dp[i] 表示到 arr[i]，subSum == target 的最小子数组长度
     * 用 map 记录每个 preSum 出现的下标，因为每个元素都是正数，因此不会重复，另外因为 length = j-i+1，所以将 map.put(0, -1)
     * 当 map 中出现 preSum-target 时，检查是不是找到了第二个区间，如果是，更新 result，之前的长度是 dp[pre]，当前长度是 i-pre
     */
    public static int minSumOfLengths(int[] arr, int target) {
        int result = Integer.MAX_VALUE, n = arr.length, preSum = 0;
        int[] dp = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            preSum += arr[i];
            dp[i] = i > 0 ? dp[i - 1] : Integer.MAX_VALUE;

            if (map.containsKey(preSum - target)) {
                int pre = map.get(preSum - target);
                dp[i] = Math.min(dp[i], i - pre);

                // 如果 pre == -1 或者 dp[pre] == MAX_VALUE，代表之前只找到一个区间；
                // 如果不是，那么就是之前已经找到一个区间，现在找到了第二个区间，将两个区间的长度相加，然后更新 result
                if (pre != -1 && dp[pre] != Integer.MAX_VALUE) {
                    result = Math.min(result, dp[pre] + i - pre);
                }
            }
            map.put(preSum, i);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
