package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given two positive integer arrays nums1 and nums2, both of length n.
 * The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]| for each 0 <= i < n (0-indexed).
 * You can replace at most one element of nums1 with any other element in nums1 to minimize the absolute sum difference.
 * Return the minimum absolute sum difference after replacing at most one element in the array nums1. Since the answer may be large, return it modulo 109 + 7.
 * |x| is defined as:
 * 1. x if x >= 0, or
 * 2. -x if x < 0.
 *
 * Example 1:
 * Input: nums1 = [1,7,5], nums2 = [2,3,5]
 * Output: 3
 * Explanation: There are two possible optimal solutions:
 * - Replace the second element with the first: [1,7,5] => [1,1,5], or
 * - Replace the second element with the third: [1,7,5] => [1,5,5].
 * Both will yield an absolute sum difference of |1-2| + (|1-3| or |5-3|) + |5-5| = 3.
 *
 * Example 2:
 * Input: nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 * Output: 0
 * Explanation: nums1 is equal to nums2 so no replacement is needed. This will result in an
 * absolute sum difference of 0.
 *
 * Example 3:
 * Input: nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 * Output: 20
 * Explanation: Replace the first element with the second: [1,10,4,4,2,7] => [10,10,4,4,2,7].
 * This yields an absolute sum difference of |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 *
 * Constraints:
 * 1. n == nums1.length
 * 2. n == nums2.length
 * 3. 1 <= n <= 10^5
 * 4. 1 <= nums1[i], nums2[i] <= 10^5
 */
public class MinimumAbsoluteSumDifference {
    public static void main(String[] args) {
        System.out.println(minAbsoluteSumDiff(new int[]{1, 7, 5}, new int[]{2, 3, 5}));
        System.out.println(minAbsoluteSumDiff(new int[]{8, 9, 2}, new int[]{7, 7, 3}));
    }

    /**
     * 二分查找
     * 先计算所有绝对差值之和，然后对于 nums2 中的每个元素 target，对 nums1 排序后用二分法查找与 target 最接近的元素，
     * 并记录替换后绝对差值减小的最大值，最后用初始的绝对差值之和减去该最大值，就是不超过一次替换时的最小绝对差值之和
     */
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int maxReduce = 0, mod = 1000000007, n = nums1.length;
        long sum = 0L;
        int[] nums = Arrays.copyOf(nums1, n);
        Arrays.parallelSort(nums);

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum += diff;
            // 更新绝对差值减小的最大值
            if (diff > maxReduce) {
                maxReduce = Math.max(maxReduce, diff - minDiff(nums, nums2[i]));
            }
        }
        sum = (sum - maxReduce) % mod;
        return (int) sum;
    }

    /**
     * 寻找 nums 数组中与 target 最接近的元素，计算最小绝对差值
     */
    private static int minDiff(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return 0;
            }
            if (target - nums[mid] > nums[mid + 1] - target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return Math.abs(nums[left] - target);
    }
}
