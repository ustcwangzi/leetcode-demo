package com.wz.dynamicprogramming;

/**
 * You may recall that an array arr is a mountain array if and only if:
 * - arr.length >= 3
 * - There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * - arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * - arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
 *
 * Example 1:
 * Input: nums = [1,3,1]
 * Output: 0
 * Explanation: The array itself is a mountain array so we do not need to remove any elements.
 *
 * Example 2:
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 *
 * Constraints:
 * 1. 3 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 10^9
 * 3. It is guaranteed that you can make a mountain array out of nums.
 */
public class MinimumNumberOfRemovalsToMakeMountainArray {
    public static void main(String[] args) {
        System.out.println(minimumMountainRemovals(new int[]{1, 3, 1}));
        System.out.println(minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
    }

    /**
     * 是对 {@link LongestIncreasingSubsequence} 的扩展
     * 分别正向、逆向求出最长递增子串的长度，那么，对于位置 i，能够组成的 Mountain-Array 最大长度就是 left[i] + right[i] - 1
     */
    public static int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] leftLIS = forwardLIS(nums), rightLIS = backwardLIS(nums);

        int count = 0;
        for (int i = 1; i < n - 1; i++) {
            if (leftLIS[i] > 1 && rightLIS[i] > 1) {
                count = Math.max(count, leftLIS[i] - 1 + rightLIS[i]);
            }
        }
        return count == 0 ? n - 1 : n - count;
    }

    private static int[] forwardLIS(int[] nums) {
        int n = nums.length;
        int[] leftLIS = new int[n];
        for (int i = 0; i < n; i++) {
            leftLIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    leftLIS[i] = Math.max(leftLIS[i], leftLIS[j] + 1);
                }
            }
        }
        return leftLIS;
    }

    private static int[] backwardLIS(int[] nums) {
        int n = nums.length;
        int[] rightLIS = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            rightLIS[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    rightLIS[i] = Math.max(rightLIS[i], rightLIS[j] + 1);
                }
            }
        }
        return rightLIS;
    }
}
