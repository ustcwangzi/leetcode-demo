package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths. The values in the arrays are between 1 and 6, inclusive.
 * In one operation, you can change any integer's value in any of the arrays to any value between 1 and 6, inclusive.
 * Return the minimum number of operations required to make the sum of values in nums1 equal to the sum of values in nums2.
 * Return -1 if it is not possible to make the sum of the two arrays equal.
 *
 * Example 1:
 * Input: nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2].
 * - Change nums1[5] to 1. nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2].
 * - Change nums1[2] to 2. nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2].
 *
 * Example 2:
 * Input: nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * Output: -1
 * Explanation: There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
 *
 * Example 3:
 * Input: nums1 = [6,6], nums2 = [1]
 * Output: 3
 * Explanation: You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
 * - Change nums1[0] to 2. nums1 = [2,6], nums2 = [1].
 * - Change nums1[1] to 2. nums1 = [2,2], nums2 = [1].
 * - Change nums2[0] to 4. nums1 = [2,2], nums2 = [4].
 *
 * Constraints:
 * 1. 1 <= nums1.length, nums2.length <= 10^5
 * 2. 1 <= nums1[i], nums2[i] <= 6
 */
public class EqualSumArraysWithMinimumNumberOfOperations {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 1, 2, 2, 2, 2}));
    }

    /**
     * 数组长度相差超过 6 倍直接返回 -1
     * 分别计算两数组之和，并通过交换保证 num1 是小数组、num2 是大数组
     * 对数组进行排序，然后用 i 指向 num1 的开始位置，j 指向 num2 的结束位置，从最小或最大的数字开始操作，直至数组和相等
     * 可以将最大数变为 1，此时 sum2 减去 num2[j]-1，同时 j 向左移动
     * 可以将最小数变为 6，此时 sum1 加上 6-num1[i]，同时 i 向右移动
     */
    public static int minOperations(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length * 6 || nums2.length > nums1.length * 6) {
            return -1;
        }

        int sum1 = Arrays.stream(nums1).sum(), sum2 = Arrays.stream(nums2).sum();
        if (sum1 > sum2) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;

            int tmpSum = sum1;
            sum1 = sum2;
            sum2 = tmpSum;
        }
        Arrays.parallelSort(nums1);
        Arrays.parallelSort(nums2);

        int result = 0, i = 0, j = nums2.length - 1;
        while (sum1 < sum2) {
            if (j < 0 || 6 - nums1[i] > nums2[j] - 1) {
                sum1 += 6 - nums1[i++];
            } else {
                sum2 -= nums2[j--] - 1;
            }
            result++;
        }

        return result;
    }
}
