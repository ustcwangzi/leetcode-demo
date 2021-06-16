package com.wz.twopointer;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
 * The score you get by erasing the subarray is equal to the sum of its elements.
 * Return the maximum score you can get by erasing exactly one subarray.
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 * Example 1:
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 *
 * Example 2:
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^4
 */
public class MaximumErasureValue {
    public static void main(String[] args) {
        System.out.println(maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
        System.out.println(maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}));
    }

    /**
     * 前缀和 + 双指针
     * 先计算前缀和 pre[i] 表示 nums[0...i-1] 元素之和，遍历数组，使用 set 保存当前选中的子数组
     * 如果 set 中已包含当前元素，从 set 中移除 nums[left]，同时 left 右移，直到 set 中不包含当前元素
     * 然后将当前元素加入到 set 中，同时借助前缀和数组求出当前子数组元素之和
     */
    public static int maximumUniqueSubarray(int[] nums) {
        int n = nums.length, result = 0;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }

        int left = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            // 移除重复元素
            while (set.contains(nums[i])) {
                set.remove(nums[left++]);
            }
            set.add(nums[i]);
            result = Math.max(result, pre[i + 1] - pre[left]);
        }
        return result;
    }
}
