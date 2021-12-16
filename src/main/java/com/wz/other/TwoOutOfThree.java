package com.wz.other;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values
 * that are present in at least two out of the three arrays. You may return the values in any order.
 *
 * Example 1:
 * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * Output: [3,2]
 * Explanation: The values that are present in at least two arrays are:
 * - 3, in all three arrays.
 * - 2, in nums1 and nums2.
 *
 * Example 2:
 * Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * Output: [2,3,1]
 * Explanation: The values that are present in at least two arrays are:
 * - 2, in nums2 and nums3.
 * - 3, in nums1 and nums2.
 * - 1, in nums1 and nums3.
 *
 * Constraints:
 * 1. 1 <= nums1.length, nums2.length, nums3.length <= 100
 * 2. 1 <= nums1[i], nums2[j], nums3[k] <= 100
 */
public class TwoOutOfThree {
    public static void main(String[] args) {
        System.out.println(twoOutOfThree(new int[]{1, 1, 3, 2}, new int[]{2, 3}, new int[]{3}));
        System.out.println(twoOutOfThree(new int[]{3, 1}, new int[]{2, 3}, new int[]{1, 2}));
    }

    /**
     * 统计数字出现次数，每个数字中相同数字只统计一次，最后出现次数超过 1 的加入到结果中
     */
    public static List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] count = new int[101];
        calCount(nums1, count);
        calCount(nums2, count);
        calCount(nums3, count);

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 1) {
                result.add(i);
            }
        }
        return result;
    }

    private static void calCount(int[] nums, int[] count) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (set.add(num)) {
                count[num]++;
            }
        }
    }
}
