package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums and a target element target.
 * A target index is an index i such that nums[i] == target.
 * Return a list of the target indices of nums after sorting nums in non-decreasing order.
 * If there are no target indices, return an empty list. The returned list must be sorted in increasing order.
 *
 * Example 1:
 * Input: nums = [1,2,5,2,3], target = 2
 * Output: [1,2]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The indices where nums[i] == 2 are 1 and 2.
 *
 * Example 2:
 * Input: nums = [1,2,5,2,3], target = 3
 * Output: [3]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 3 is 3.
 *
 * Example 3:
 * Input: nums = [1,2,5,2,3], target = 5
 * Output: [4]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 5 is 4.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. 1 <= nums[i], target <= 100
 */
public class FindTargetIndicesAfterSortingArray {
    public static void main(String[] args) {
        System.out.println(targetIndices(new int[]{1, 2, 5, 2, 3}, 2));
    }

    /**
     * 遍历数组，统计等于 target 的元素个数 countTarget、小于 target 的元素个数 countLess
     * 需要的排序后的位置，那么排序后 [countLess,...,countLess+countTarget-1] 位置的元素值全都是 target
     */
    public static List<Integer> targetIndices(int[] nums, int target) {
        int countTarget = 0, countLess = 0;
        for (int num : nums) {
            if (num == target) {
                countTarget++;
            } else if (num < target) {
                countLess++;
            }
        }

        List<Integer> result = new ArrayList<>(countTarget);
        for (int i = 0; i < countTarget; i++) {
            result.add(countLess++);
        }
        return result;
    }
}
