package com.wz.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a
 * continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 *
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 *
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 * Constraints:
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{23, 2, 4, 6, 7};
        System.out.println(checkSubarraySum(nums, 6));
        nums = new int[]{23, 2, 6, 4, 7};
        System.out.println(checkSubarraySum(nums, 6));
    }

    /**
     * 若数字a和b分别除以数字c，若得到的余数相同，那么(a-b)必定能够整除c。
     * 用一个集合set来保存所有出现过的余数，如果当前的累加和除以k得到的余数在set中已经存在了，那么说明之前必定有一段子数组和可以整除k。
     * 需要注意的是k为0的情况，由于无法取余，就把当前累加和放入set中。
     * 还有就是题目要求子数组至少需要两个数字，那么需要一个变量 preSum 来记录之前的和，每次存入set中的是 preSum，而不是当前的累积和
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0, preSum = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            sum += num;
            int mod = (k == 0) ? sum : sum % k;
            if (set.contains(mod)) {
                return true;
            }
            set.add(preSum);
            preSum = mod;
        }
        return false;
    }
}
