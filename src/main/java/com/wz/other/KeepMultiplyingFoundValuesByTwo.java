package com.wz.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given an array of integers nums. You are also given an integer original which is the first number that needs to be searched for in nums.
 * You then do the following steps:
 * 1. If original is found in nums, multiply it by two (i.e., set original = 2 * original).
 * 2. Otherwise, stop the process.
 * 3. Repeat this process with the new number as long as you keep finding the number.
 * Return the final value of original.
 *
 * Example 1:
 * Input: nums = [5,3,6,1,12], original = 3
 * Output: 24
 * Explanation:
 * - 3 is found in nums. 3 is multiplied by 2 to obtain 6.
 * - 6 is found in nums. 6 is multiplied by 2 to obtain 12.
 * - 12 is found in nums. 12 is multiplied by 2 to obtain 24.
 * - 24 is not found in nums. Thus, 24 is returned.
 *
 * Example 2:
 * Input: nums = [2,7,9], original = 4
 * Output: 4
 * Explanation:
 * - 4 is not found in nums. Thus, 4 is returned.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i], original <= 1000
 */
public class KeepMultiplyingFoundValuesByTwo {
    public static void main(String[] args) {
        System.out.println(findFinalValue(new int[]{5, 3, 6, 1, 12}, 3));
        System.out.println(findFinalValue(new int[]{2, 7, 9}, 4));
    }

    /**
     * 使用 set 依次判断 original、original*2、original*2*2 ... 是否存在即可
     */
    public static int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(set::add);
        while (set.contains(original)) {
            original = 2 * original;
        }
        return original;
    }
}
