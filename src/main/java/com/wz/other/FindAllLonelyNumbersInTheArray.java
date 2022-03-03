package com.wz.other;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You are given an integer array nums. A number x is lonely when it appears only once, and no adjacent numbers (i.e. x + 1 and x - 1) appear in the array.
 * Return all lonely numbers in nums. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [10,6,5,8]
 * Output: [10,8]
 * Explanation:
 * - 10 is a lonely number since it appears exactly once and 9 and 11 does not appear in nums.
 * - 8 is a lonely number since it appears exactly once and 7 and 9 does not appear in nums.
 * - 5 is not a lonely number since 6 appears in nums and vice versa.
 * Hence, the lonely numbers in nums are [10, 8].
 * Note that [8, 10] may also be returned.
 *
 * Example 2:
 * Input: nums = [1,3,5,3]
 * Output: [1,5]
 * Explanation:
 * - 1 is a lonely number since it appears exactly once and 0 and 2 does not appear in nums.
 * - 5 is a lonely number since it appears exactly once and 4 and 6 does not appear in nums.
 * - 3 is not a lonely number since it appears twice.
 * Hence, the lonely numbers in nums are [1, 5].
 * Note that [5, 1] may also be returned.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^6
 */
public class FindAllLonelyNumbersInTheArray {
    public static void main(String[] args) {
        System.out.println(findLonely(new int[]{10, 6, 5, 8}));
    }

    /**
     * 使用 map 记录每个元素出现次数，然后遍历 map，选择其中只出现一次并且 x-1、x+1 未出现在 map 中的元素
     */
    public static List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> result = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1 && !map.containsKey(entry.getKey() - 1) && !map.containsKey(entry.getKey() + 1)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
