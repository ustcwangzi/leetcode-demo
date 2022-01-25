package com.wz.stack;

import java.util.Stack;

/**
 * You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
 * Return the sum of all subarray ranges of nums.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [2], range = 2 - 2 = 0
 * [3], range = 3 - 3 = 0
 * [1,2], range = 2 - 1 = 1
 * [2,3], range = 3 - 2 = 1
 * [1,2,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
 *
 * Example 2:
 * Input: nums = [1,3,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [3], range = 3 - 3 = 0
 * [3], range = 3 - 3 = 0
 * [1,3], range = 3 - 1 = 2
 * [3,3], range = 3 - 3 = 0
 * [1,3,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
 *
 * Example 3:
 * Input: nums = [4,-2,-3,4,1]
 * Output: 59
 * Explanation: The sum of all subarray ranges of nums is 59.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. -10^9 <= nums[i] <= 10^9
 */
public class SumOfSubarrayRanges {
    public static void main(String[] args) {
        System.out.println(subArrayRanges(new int[]{1, 2, 3}));
        System.out.println(subArrayRanges2(new int[]{1, 2, 3}));
    }

    /**
     * 方案一：直接双层遍历，遍历过程中记录最大、最小值，并将差值累加到结果中
     */
    public static long subArrayRanges(int[] nums) {
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i], max = nums[i];
            for (int j = i; j < nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                result += max - min;
            }
        }
        return result;
    }

    /**
     * 方案二：单调栈
     * 所有子数组(最大值-最小值)之和，相当于求 所有子数组最大值之和-所有子数组最小值之和
     * 即求 nums[i] * count(i) - nums[i] * count(j)
     * 其中 count(i) 代表以 nums[i] 为最大值的子数组个数，count(j) 代表以 nums[i] 为最小值的子数组个数
     */
    public static long subArrayRanges2(int[] nums) {
        long result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > (i == nums.length ? Integer.MIN_VALUE : nums[i])) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                result -= (long) nums[j] * (i - j) * (j - k);

            }
            stack.push(i);
        }

        stack.clear();
        for (int i = 0; i <= nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < (i == nums.length ? Integer.MAX_VALUE : nums[i])) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                result += (long) nums[j] * (i - j) * (j - k);

            }
            stack.push(i);
        }
        return result;
    }
}
