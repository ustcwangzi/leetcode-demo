package com.wz.array;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * Example 1:
 * Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 *              The maximum number of consecutive 1s is 3.
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1, 1, 1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int result = 0, cur = 0;
        for (int num : nums) {
            if (num == 1) {
                cur++;
                result = Math.max(result, cur);
            } else {
                cur = 0;
            }
        }
        return result;
    }
}
