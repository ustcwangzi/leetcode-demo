package com.wz.other;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an integer array nums consisting of 2 * n integers.
 * You need to divide nums into n pairs such that:
 * - Each element belongs to exactly one pair.
 * - The elements present in a pair are equal.
 * Return true if nums can be divided into n pairs, otherwise return false.
 *
 * Example 1:
 * Input: nums = [3,2,3,2,2,2]
 * Output: true
 * Explanation:
 * There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
 * If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.
 *
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation:
 * There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.
 *
 * Constraints:
 * 1. nums.length == 2 * n
 * 2. 1 <= n <= 500
 * 3. 1 <= nums[i] <= 500
 */
public class DivideArrayIntoEqualPairs {
    public static void main(String[] args) {
        System.out.println(divideArray(new int[]{3, 2, 3, 2, 2, 2}));
        System.out.println(divideArray(new int[]{1, 2, 3, 4}));
    }

    /**
     * 使用 set 记录元素，第二次出现时就将元素移除，最后若 set 为空，说明每个元素都出了偶数次
     */
    public static boolean divideArray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.size() == 0;
    }
}
