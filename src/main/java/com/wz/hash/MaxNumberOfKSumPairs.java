package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer k.
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * Return the maximum number of operations you can perform on the array.
 *
 * Example 1:
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 *
 * Example 2:
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^9
 * 3. 1 <= k <= 10^9
 */
public class MaxNumberOfKSumPairs {
    public static void main(String[] args) {
        System.out.println(maxOperations(new int[]{1, 2, 3, 4}, 5));
        System.out.println(maxOperations(new int[]{3, 1, 3, 4, 3}, 6));
    }

    /**
     * 与 {@link com.wz.array.TwoSum} 类似，只是 TwoSum 中记录每个元素的下标，本题记录每个元素出现的次数
     * 遍历数组，如果 k-num 存在于 map 中，说明找到了一组结果，同时将对应次数减一
     */
    public static int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int num : nums) {
            int target = k - num;
            if (!map.containsKey(target)) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                continue;
            }

            result++;
            if (map.get(target) == 1) {
                map.remove(target);
            } else {
                map.put(target, map.get(target) - 1);
            }
        }

        return result;
    }
}
