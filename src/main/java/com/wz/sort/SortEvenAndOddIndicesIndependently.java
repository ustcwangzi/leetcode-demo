package com.wz.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums. Rearrange the values of nums according to the following rules:
 * 1. Sort the values at odd indices of nums in non-increasing order.
 *    For example, if nums = [4,1,2,3] before this step, it becomes [4,3,2,1] after. The values at odd indices 1 and 3 are sorted in non-increasing order.
 * 2. Sort the values at even indices of nums in non-decreasing order.
 *    For example, if nums = [4,1,2,3] before this step, it becomes [2,1,4,3] after. The values at even indices 0 and 2 are sorted in non-decreasing order.
 * Return the array formed after rearranging the values of nums.
 *
 * Example 1:
 * Input: nums = [4,1,2,3]
 * Output: [2,3,4,1]
 * Explanation:
 * First, we sort the values present at odd indices (1 and 3) in non-increasing order.
 * So, nums changes from [4,1,2,3] to [4,3,2,1].
 * Next, we sort the values present at even indices (0 and 2) in non-decreasing order.
 * So, nums changes from [4,1,2,3] to [2,3,4,1].
 * Thus, the array formed after rearranging the values is [2,3,4,1].
 *
 * Example 2:
 * Input: nums = [2,1]
 * Output: [2,1]
 * Explanation:
 * Since there is exactly one odd index and one even index, no rearrangement of values takes place.
 * The resultant array formed is [2,1], which is the same as the initial array.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. 1 <= nums[i] <= 100
 */
public class SortEvenAndOddIndicesIndependently {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortEvenOdd(new int[]{4, 1, 2, 3})));
    }

    /**
     * 使用 list 分别收集奇数位置元素和偶数位数元素，然后对两个 list 排序，最后将排序后的结果更新到结果集中
     */
    public static int[] sortEvenOdd(int[] nums) {
        List<Integer> evenList = new ArrayList<>(), oddList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                evenList.add(nums[i]);
            } else {
                oddList.add(nums[i]);
            }
        }
        Collections.sort(evenList);
        Collections.sort(oddList);

        int evenIndex = 0, oddIndex = oddList.size() - 1;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 0) {
                nums[i] = evenList.get(evenIndex++);
            } else {
                nums[i] = oddList.get(oddIndex--);
            }
        }
        return nums;
    }
}
