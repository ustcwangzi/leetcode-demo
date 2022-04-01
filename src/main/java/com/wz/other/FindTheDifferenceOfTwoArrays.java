package com.wz.other;

import java.util.*;

/**
 * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 * - answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 * - answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 * - Note that the integers in the lists may be returned in any order.
 *
 * Example 1:
 * Input: nums1 = [1,2,3], nums2 = [2,4,6]
 * Output: [[1,3],[4,6]]
 * Explanation:
 * For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
 * For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].
 *
 * Example 2:
 * Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * Output: [[3],[]]
 * Explanation:
 * For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
 * Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
 *
 * Constraints:
 * 1. 1 <= nums1.length, nums2.length <= 1000
 * 2. -1000 <= nums1[i], nums2[i] <= 1000
 */
public class FindTheDifferenceOfTwoArrays {
    public static void main(String[] args) {
        System.out.println(findDifference(new int[]{1, 2, 3}, new int[]{2, 4, 6}));
        System.out.println(findDifference(new int[]{1, 2, 3, 3}, new int[]{1, 1, 2, 2}));
    }

    /**
     * 将两个数组分别使用 set 存储，然后分别遍历 set，收集另一个 set 中不包含的元素即可
     */
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        Arrays.stream(nums1).forEach(set1::add);
        Arrays.stream(nums2).forEach(set2::add);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        for (int num : set1) {
            if (!set2.contains(num)) {
                list.add(num);
            }
        }
        result.add(list);

        list = new LinkedList<>();
        for (int num : set2) {
            if (!set1.contains(num)) {
                list.add(num);
            }
        }
        result.add(list);
        return result;
    }
}
