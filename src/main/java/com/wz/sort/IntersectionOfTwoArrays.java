package com.wz.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Explanation: [4,9] is also accepted.
 *
 * Constraints:
 * 1. 1 <= nums1.length, nums2.length <= 1000
 * 2. 0 <= nums1[i], nums2[i] <= 1000
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
    }

    /**
     * 用 set1 判断元素是否同时存在两个数组中，用 set2 存储结果，因为需要去重
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(nums1.length);
        Arrays.stream(nums1).forEach(set1::add);
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) {
            if (set1.contains(num)) {
                set2.add(num);
            }
        }

        int index = 0;
        int[] result = new int[set2.size()];
        for (int num : set2) {
            result[index++] = num;
        }

        return result;
    }
}
