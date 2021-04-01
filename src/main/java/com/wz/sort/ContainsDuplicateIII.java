package com.wz.sort;

import java.util.TreeSet;

/**
 * Given an integer array nums and two integers k and t, return true if there are two distinct indices
 * i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 *
 * Constraints:
 * 1. 0 <= nums.length <= 2 * 10^4
 * 2. -2^31 <= nums[i] <= 2^31 - 1
 * 3. 0 <= k <= 10^4
 * 4. 0 <= t <= 2^31 - 1
 */
public class ContainsDuplicateIII {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(containsNearbyAlmostDuplicate(nums, 3, 0));
        System.out.println(containsNearbyAlmostDuplicate2(nums, 3, 0));

        nums = new int[]{1, 5, 9, 1, 5, 9};
        System.out.println(containsNearbyAlmostDuplicate(nums, 2, 3));
        System.out.println(containsNearbyAlmostDuplicate2(nums, 2, 3));
    }

    /**
     * 双层循环暴力解，超时
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long diff = 0;
                diff = Math.abs(diff + nums[j] - nums[i]);
                if (diff <= t && j - i <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 滑动窗口 + TreeSet
     * 使用 TreeSet 快速找到大于等于 num-t 的元素 val，若 val 同时满足小于等于 num+t，则符合要求直接返回 true
     * TreeSet 底层是红黑树，使得搜索时间复杂度为 O(log K)
     */
    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 集合中大于等于给定元素的最小元素
            Long val = set.ceiling((long) nums[i] - (long) t);
            if (val != null && val <= ((long) nums[i] + (long) t)) {
                return true;
            }

            set.add((long) nums[i]);
            // 维护 k 个元素
            if (set.size() > k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
