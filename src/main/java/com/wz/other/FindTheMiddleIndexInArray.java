package com.wz.other;

import java.util.Arrays;

/**
 * Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).
 * A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].
 * If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.
 * Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.
 *
 * Example 1:
 * Input: nums = [2,3,-1,8,4]
 * Output: 3
 * Explanation:
 * The sum of the numbers before index 3 is: 2 + 3 + -1 = 4
 * The sum of the numbers after index 3 is: 4 = 4
 *
 * Example 2:
 * Input: nums = [1,-1,4]
 * Output: 2
 * Explanation:
 * The sum of the numbers before index 2 is: 1 + -1 = 0
 * The sum of the numbers after index 2 is: 0
 *
 * Example 3:
 * Input: nums = [2,5]
 * Output: -1
 * Explanation:
 * There is no valid middleIndex.
 *
 * Example 4:
 * Input: nums = [1]
 * Output: 0
 * Explantion:
 * The sum of the numbers before index 0 is: 0
 * The sum of the numbers after index 0 is: 0
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. -1000 <= nums[i] <= 1000
 */
public class FindTheMiddleIndexInArray {
    public static void main(String[] args) {
        System.out.println(findMiddleIndex(new int[]{1}));
        System.out.println(findMiddleIndex(new int[]{1, -1, 4}));
        System.out.println(findMiddleIndex(new int[]{2, 3, -1, 8, 4}));
    }

    /**
     * 先计算全部元素之和 sum，然后遍历数组，分别计算 leftSum[i] = nums[0...i-1]，rightSum[i] = sum-leftSum-nums[i]
     * 若 leftSum == rightSum，则返回 i，否则遍历结束后返回 -1
     */
    public static int findMiddleIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum(), leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = sum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}
