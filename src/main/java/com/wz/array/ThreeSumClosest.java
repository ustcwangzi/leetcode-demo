package com.wz.array;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        System.out.println(threeSumClosest(nums, 1));
    }

    /**
     * 与{@link ThreeSum}思想类似，只是在计算时维护最新结果result和当前差值diff
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.parallelSort(nums);
        int sum, left, right;
        int result = Integer.MAX_VALUE, diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    if (sum - target < diff) {
                        diff = sum - target;
                        result = sum;
                    }
                    right--;
                } else {
                    if (target - sum < diff) {
                        diff = target - sum;
                        result = sum;
                    }
                    left++;
                }
            }
        }
        return result;
    }
}
