package com.wz.binarysearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once. Find this single element that appears only once.
 * Follow up: Your solution should run in O(log n) time and O(1) space.
 *
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^5
 */
public class SingleElementInSortedArray {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    /**
     * 直接进行异或，出现两次的元素全部消除了，只剩下一个出现一次的元素
     */
    public static int singleNonDuplicate(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
