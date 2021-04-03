package com.wz.sort;

import java.util.*;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 * Constraints:
 * 1. 1 <= nums1.length, nums2.length <= 1000
 * 2. 0 <= nums1[i], nums2[i] <= 1000
 */
public class IntersectionOfTwoArraysII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }

    /**
     * 与 {@link IntersectionOfTwoArrays} 不同之处在于结果元素的出现次数需要是两个数组中次数较少的，这样才能算交集
     * 使用 map 统计 nums1 中元素出现次数，然后遍历 nums2，如果元素包含在 map 中并且次数大于 0，就加入到结果中，并把次数减一
     * 减一操作保证结果中元素的出现次数满足要求
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        int[] result = new int[list.size()];
        int index = 0;
        for (int num : list) {
            result[index++] = num;
        }

        return result;
    }
}
