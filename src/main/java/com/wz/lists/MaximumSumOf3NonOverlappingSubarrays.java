package com.wz.lists;

import java.util.Arrays;

/**
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 *
 * Example:
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 */
public class MaximumSumOf3NonOverlappingSubarrays {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 2, 6, 7, 5, 1};
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums, 2)));

        nums = new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1};
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums, 2)));
    }

    /**
     * sums[i]表示以i为起点的长度为k的子数组的和
     * 固定idx2的位置，那么idx1范围是[0,idx2−k]，idx3范围是[idx2+1,n−1]，而idx2范围是[k,n−k−1]
     *
     * 当idx2位置固定，对于idx1应该是[0,idx2-k]那个最大数的下标。这个问题可以用动态规划解决。
     * 用一个数组left保存从[0,i]子数组之和最大的下标，动态转移方程式：
     * 如果sums[i]>sums[left[i−1]]，则left[i]=i；否则left[i]=left[i−1]。
     *
     * 右边也是一样，当idx2位置固定，对于idx3应该是[idx2+1,n-1]那个最大数的下标。
     * 用一个数组right保存从[i,n-1]子数组之和最大的下标，动态转移方程式：
     * 如果sums[i]>=sums[right[i+1]]，则right[i]=i；否则right[i]=right[i+1]，>=是因为结果要求返回下标最小值
     *
     * 最后，移动idx2，找到结果
     */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                sums[i - k + 1] = sum;
            }
        }

        // 与左边的数组比较
        int[] left = new int[n - k + 1];
        int best = 0;
        for (int i = 0; i < left.length; i++) {
            if (sums[i] > sums[best]) {
                best = i;
            }
            left[i] = best;
        }

        // 跟右边的数组比较
        int[] right = new int[n - k + 1];
        best = right.length - 1;
        for (int i = right.length - 1; i >= 0; i--) {
            if (sums[i] >= sums[best]) {
                best = i;
            }
            right[i] = best;
        }

        int idx1 = -1, idx2 = -1, idx3 = -1;
        for (int i = k; i < right.length - k; i++) {
            int l = left[i - k], r = right[i + k];
            if (idx1 == -1 || (sums[idx1] + sums[idx2] + sums[idx3] < sums[l] + sums[i] + sums[r])) {
                idx1 = l;
                idx2 = i;
                idx3 = r;
            }
        }

        return new int[]{idx1, idx2, idx3};
    }
}
