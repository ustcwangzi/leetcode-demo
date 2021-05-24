package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
 * A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: nums = [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 *
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [1,1,1,1]
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= nums.length <= 2 * 10^4
 * 2. -10^9 <= nums[i] <= 10^9
 */
public class LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        System.out.println(findLHS(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
        System.out.println(findLHS(new int[]{1, 2, 3, 4}));
    }

    /**
     * 不要求连续，先使用 map 统计每个数字出现的频次，然后遍历 map，如果对于当前 key，map 中存在 key+1
     * 说明满足条件，将 key 的出现次数 和 key+1 的出现次数相加，更新结果
     */
    public static int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(entry.getKey() + 1)) {
                result = Math.max(result, map.get(entry.getKey()) + map.get(entry.getKey() + 1));
            }
        }
        return result;
    }
}
