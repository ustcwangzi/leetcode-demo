package com.wz.greedy;

import java.util.stream.IntStream;

/**
 * You are given an integer array nums and two integers limit and goal. The array nums has an interesting property that abs(nums[i]) <= limit.
 * Return the minimum number of elements you need to add to make the sum of the array equal to goal.
 * The array must maintain its property that abs(nums[i]) <= limit.
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 * Example 1:
 * Input: nums = [1,-1,1], limit = 3, goal = -4
 * Output: 2
 * Explanation: You can add -2 and -3, then the sum of the array will be 1 - 1 + 1 - 2 - 3 = -4.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= limit <= 10^6
 * 3. -limit <= nums[i] <= limit
 * 4. -10^9 <= goal <= 10^9
 */
public class MinimumElementsToAddToFormGivenSum {
    public static void main(String[] args) {
        System.out.println(minElements(new int[]{1, -1, 1}, 3, -4));
    }

    public static int minElements(int[] nums, int limit, int goal) {
        long diff = goal - IntStream.of(nums).mapToLong(i -> i).sum();
        return (int) ((Math.abs(diff) + limit - 1) / limit);
    }
}
