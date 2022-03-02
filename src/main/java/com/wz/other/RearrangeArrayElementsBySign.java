package com.wz.other;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
 * You should rearrange the elements of nums such that the modified array follows the given conditions:
 * 1. Every consecutive pair of integers have opposite signs.
 * 2. For all integers with the same sign, the order in which they were present in nums is preserved.
 * 3. The rearranged array begins with a positive integer.
 * Return the modified array after rearranging the elements to satisfy the aforementioned conditions.
 *
 * Example 1:
 * Input: nums = [3,1,-2,-5,2,-4]
 * Output: [3,-2,1,-5,2,-4]
 * Explanation:
 * The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
 * The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
 * Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.
 *
 * Example 2:
 * Input: nums = [-1,1]
 * Output: [1,-1]
 * Explanation:
 * 1 is the only positive integer and -1 the only negative integer in nums.
 * So nums is rearranged to [1,-1].
 *
 * Constraints:
 * 1. 2 <= nums.length <= 2 * 10^5
 * 2. nums.length is even
 * 3. 1 <= |nums[i]| <= 10^5
 * 4. nums consists of equal number of positive and negative integers.
 */
public class RearrangeArrayElementsBySign {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(rearrangeArray(new int[]{3, 1, -2, -5, 2, -4})));
    }

    /**
     * 将正数、负数交叉放置，不能改变原顺序，因此使用 positiveIndex、negativeIndex 分别记录当前需要放置的正、负数位置
     * 遍历数组，发现正数则放再 positiveIndex，同时 positiveIndex 右移两位，负数也是类似操作
     */
    public static int[] rearrangeArray(int[] nums) {
        int[] result = new int[nums.length];
        int positiveIndex = 0, negativeIndex = 1;
        for (int num : nums) {
            if (num < 0) {
                result[negativeIndex] = num;
                negativeIndex += 2;
            } else {
                result[positiveIndex] = num;
                positiveIndex += 2;
            }
        }
        return result;
    }
}
