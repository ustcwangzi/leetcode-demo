package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two.
 * The relative order of the digits from the same array must be preserved. Return an array of the k digits.
 * Note: You should try to optimize your time and space complexity.
 *
 * Example 1:
 * Input:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * Output:
 * [9, 8, 6, 5, 3]
 *
 * Example 2:
 * Input:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * Output:
 * [6, 7, 6, 0, 4]
 *
 * Example 3:
 * Input:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * Output:
 * [9, 8, 9]
 */
public class CreateMaximumNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5)));
    }

    /**
     * 问题可以转化为这样的两个子问题：
     * 1. 从数组 nums 中挑选出 t 个数，在保持元素相对顺序不变的情况下，使得选出的子数组最大化
     * 2. 在保持元素相对顺序不变的前提下，将数组 nums1 与数组 nums2 合并，使合并后的数组最大化
     * 枚举 nums1 子数组与 nums2 子数组的长度 len1、len2，在满足长度之和 len1+len2 等于 k 的前提下，分别求解最大子数组，并进行合并
     * 然后从合并得到的子数组中取最大数组即为所求
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[k];
        for (int i = Math.max(0, k - n); i <= Math.min(m, k); ++i) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (isGreater(candidate, 0, result, 0)) {
                result = candidate;
            }
        }
        return result;
    }

    private static int[] merge(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r) {
            result[r] = isGreater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return result;
    }

    public static boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    public static int[] maxArray(int[] array, int k) {
        int p = -1;
        int[] nums = new int[k];
        if (k == 0) {
            return nums;
        }

        for (int i = 0; i < array.length; i++) {
            while (p >= 0 && nums[p] < array[i] && (array.length - i + p + 1) > k) {
                p--;
            }
            if (p < nums.length - 1) {
                p++;
                nums[p] = array[i];
            }
        }
        return nums;
    }
}
