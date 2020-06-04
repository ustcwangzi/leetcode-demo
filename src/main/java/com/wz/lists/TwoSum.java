package com.wz.lists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum1(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));
    }

    /**
     * 方案一：用map的key为当前索引，value为所需要的值，每次需要遍历map检查是否是需要的值
     */
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int num : nums) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == num) {
                    return new int[]{entry.getKey(), index};
                }
            }
            map.put(index++, target - num);
        }
        return null;
    }

    /**
     * 方案二：用map的key为当前值，value为当前索引，直接检查map中是否有满足条件的值
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int num : nums) {
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), index};
            }
            map.put(num, index++);
        }
        return null;
    }
}
