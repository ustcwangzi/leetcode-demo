package com.wz.twopointer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and an integer goal.
 * You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal.
 * That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
 * Return the minimum possible value of abs(sum - goal).
 * Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.
 *
 * Example 1:
 * Input: nums = [5,-7,3,5], goal = 6
 * Output: 0
 * Explanation: Choose the whole array as a subsequence, with a sum of 6.
 * This is equal to the goal, so the absolute difference is 0.
 *
 * Example 2:
 * Input: nums = [7,-9,15,-2], goal = -5
 * Output: 1
 * Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
 * The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
 *
 * Example 3:
 * Input: nums = [1,2,3], goal = -7
 * Output: 7
 *
 * Constraints:
 * 1. 1 <= nums.length <= 40
 * 2. -10^7 <= nums[i] <= 10^7
 * 3. -10^9 <= goal <= 10^9
 */
public class ClosestSubsequenceSum {
    public static void main(String[] args) {
        System.out.println(minAbsDifference(new int[]{5, -7, 3, 5}, 6));
        System.out.println(minAbsDifference(new int[]{7, -9, 15, -2}, -5));
        System.out.println(minAbsDifference(new int[]{1, 2, 3}, -7));
    }

    /**
     * 双指针 + DFS
     * 将数组分为两部分，使用 DFS 分别求出 SubsequenceSum，然后使用双指针求出最小的结果
     * 以 [5, -7, 3, 5] 为例，分为 [5, -7], [3, 5]，对应的 SubsequenceSum 为 [-7, -2, 0, 5], [0, 3, 5, 8]
     */
    public static int minAbsDifference(int[] nums, int goal) {
        int[] array1 = new int[nums.length / 2], array2 = new int[nums.length - array1.length];
        System.arraycopy(nums, 0, array1, 0, array1.length);
        System.arraycopy(nums, array1.length, array2, 0, array2.length);
        List<Integer> sumList1 = new ArrayList<>(), sumList2 = new ArrayList<>();
        dfs(sumList1, array1, 0, 0);
        dfs(sumList2, array2, 0, 0);
        Collections.sort(sumList1);
        Collections.sort(sumList2);

        int left = 0, right = sumList2.size() - 1, result = Integer.MAX_VALUE;
        while (left < sumList1.size() && right >= 0) {
            int sum = sumList1.get(left) + sumList2.get(right);
            result = Math.min(result, Math.abs(sum - goal));
            if (sum == goal) {
                return 0;
            }
            if (sum > goal) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    private static void dfs(List<Integer> sumList, int[] array, int sum, int i) {
        if (i >= array.length) {
            sumList.add(sum);
            return;
        }

        dfs(sumList, array, sum + array[i], i + 1);
        dfs(sumList, array, sum, i + 1);
    }
}
