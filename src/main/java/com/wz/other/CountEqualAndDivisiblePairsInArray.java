package com.wz.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a 0-indexed integer array nums of length n and an integer k,
 * return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.
 *
 * Example 1:
 * Input: nums = [3,1,2,2,2,1,3], k = 2
 * Output: 4
 * Explanation:
 * There are 4 pairs that meet all the requirements:
 * - nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
 * - nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
 * - nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
 * - nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.
 *
 * Example 2:
 * Input: nums = [1,2,3,4], k = 1
 * Output: 0
 * Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. 1 <= nums[i], k <= 100
 */
public class CountEqualAndDivisiblePairsInArray {
    public static void main(String[] args) {
        System.out.println(countPairs(new int[]{3, 1, 2, 2, 2, 1, 3}, 2));
        System.out.println(countPairs(new int[]{1, 2, 3, 4}, 1));
    }

    /**
     * 使用 map 记录相同元素的下标
     * 对于 nums[i]，若 map 中已存在相同元素，则遍历已出现元素下标，满足条件 i * j % k == 0，则结果加一
     */
    public static int countPairs(int[] nums, int k) {
        int result = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            for (int j : map.get(nums[i])) {
                if (i * j % k == 0) {
                    result++;
                }
            }
            map.get(nums[i]).add(i);
        }
        return result;
    }
}
