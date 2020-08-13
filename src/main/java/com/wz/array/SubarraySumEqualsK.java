package com.wz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        System.out.println(subarraySum(nums, 2));
    }

    /**
     * 记preSum[i]为nums[0]～nums[i]之和，可以在O(1)时间求出任意一个subarray之和：
     * sum of subarray(i, j) = preSum[j] - preSum[i - 1]
     * 有了preSum，问题就转化为：对每个j，之前存在多少个i满足preSum[i] == preSum[j] - k
     * 这意味着对于每个j，需要记录之前所有的preSum，然后在这些preSum中查找有多少个数值等于 preSum[j] - k
     * 为了更有效的解决这个问题，使用一个HashMap来记录preSum[i]出现过的次数
     */
    public static int subarraySum(int[] nums, int k) {
        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();
        // 为了包含 preSum-k == 0, 也就是 preSum == k的情况
        map.put(0, 1);
        int preSum = 0;
        for (int num : nums) {
            preSum += num;
            result += map.getOrDefault(preSum - k, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return result;
    }
}
