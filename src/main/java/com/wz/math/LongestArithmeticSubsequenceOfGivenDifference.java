package com.wz.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is
 * an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 *
 * Example 1:
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 *
 * Example 2:
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 */
public class LongestArithmeticSubsequenceOfGivenDifference {
    public static void main(String[] args) {
        System.out.println(longestSubsequence(new int[]{1, 2, 3, 4}, 1));
        System.out.println(longestSubsequence(new int[]{1, 3, 5, 7}, 1));
    }

    /**
     * dp[arr[i]] 代表以 arr[i] 为结尾的最长等差序列长度
     * 若前面存在 arr[i]-difference，则 dp[arr[i]] = dp[arr[i]-difference] + 1
     * 若前面不存在 arr[i]-difference，则 dp[arr[i]] = 1
     */
    public static int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int result = 0;
        for (int num : arr) {
            dp.put(num, dp.getOrDefault(num - difference, 0) + 1);
            result = Math.max(result, dp.get(num));
        }
        return result;
    }
}
