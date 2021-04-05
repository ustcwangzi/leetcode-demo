package com.wz.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values.
 * If multiple values have the same frequency, sort them in decreasing order.
 * Return the sorted array.
 *
 * Example 1:
 * Input: nums = [1,1,2,2,2,3]
 * Output: [3,1,1,2,2,2]
 * Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
 *
 * Example 2:
 * Input: nums = [2,3,1,3,2]
 * Output: [1,3,3,2,2]
 * Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. -100 <= nums[i] <= 100
 */
public class SortArrayByIncreasingFrequency {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(frequencySort(new int[]{1, 1, 2, 2, 2, 3})));
        System.out.println(Arrays.toString(frequencySort(new int[]{2, 3, 1, 3, 2})));
    }

    /**
     * 使用 map 统计每个元素的出现次数，将数组元素保存到 list 中，然后对 list 按照题目给定规则进行排序
     */
    public static int[] frequencySort(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        list.sort((o1, o2) -> {
            int frequency1 = map.get(o1), frequency2 = map.get(o2);
            if (frequency1 == frequency2) {
                return Integer.compare(o2, o1);
            }
            return frequency1 - frequency2;
        });

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
