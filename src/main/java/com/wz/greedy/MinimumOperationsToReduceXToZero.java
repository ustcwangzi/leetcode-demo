package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the
 * rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 * Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.
 *
 * Example 1:
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 *
 * Example 2:
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 *
 * Example 3:
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements to reduce x to zero.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^4
 * 3. 1 <= x <= 109
 */
public class MinimumOperationsToReduceXToZero {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 1, 4, 2, 3}, 5));
        System.out.println(minOperations(new int[]{5, 6, 7, 8, 9}, 4));
        System.out.println(minOperations(new int[]{5, 2, 3, 1, 1}, 5));
    }

    /**
     * 求头部或尾部连续的最少元素之和，可以转化为求数组中一个最长的子数组，使其元素之和 等于 数组之和 减去 给定值
     * 而求这样的最长子数组可以用滑动窗口来解决，开始时 i、j 都指向数组开始位置，然后 j 每向右滑动一个位置，就累加当前元素之和
     * 若超出 target，则 i 右移，同时从元素和中去除 nums[i]，当元素之和等于 target 时，更新最长子数组长度
     */
    public static int minOperations(int[] nums, int x) {
        int target = Arrays.stream(nums).sum() - x;
        if (target <= 0) {
            return target == 0 ? nums.length : -1;
        }

        int sum = 0, maxLength = -1, i = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (i <= j && sum > target) {
                sum -= nums[i++];
            }
            if (sum == target) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
}
