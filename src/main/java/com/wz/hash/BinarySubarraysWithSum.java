package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 * A subarray is a contiguous part of the array.
 *
 * Example 1:
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *
 * Example 2:
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 *
 * Constraints:
 * 1. 1 <= nums.length <= 3 * 10^4
 * 2. nums[i] is either 0 or 1.
 * 3. 0 <= goal <= nums.length
 */
public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        System.out.println(numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
    }

    /**
     * 与 {@link com.wz.array.SubarraySumEqualsK} 类似
     * 使用 preSum 记录前缀和，若 sum[i...j] == goal，则 preSum[j] - preSum[i] == goal
     * 问题转化为：对于每个 preSum[j] 寻找有多少个 preSum[i] == preSum[j] - goal，使用 map 记录每个 preSum 出现次数
     */
    public static int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        // preSum == k
        map.put(0, 1);
        int preSum = 0, result = 0;
        for (int num : nums) {
            preSum += num;
            result += map.getOrDefault(preSum - goal, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }
}
