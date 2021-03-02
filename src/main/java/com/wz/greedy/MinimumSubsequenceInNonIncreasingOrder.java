package com.wz.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the array nums, obtain a subsequence of the array whose sum of elements is strictly greater than
 * the sum of the non included elements in such subsequence.
 * If there are multiple solutions, return the subsequence with minimum size and if there still exist multiple solutions,
 * return the subsequence with the maximum total sum of all its elements.
 * A subsequence of an array can be obtained by erasing some (possibly zero) elements from the array.
 * Note that the solution with the given constraints is guaranteed to be unique. Also return the answer sorted in non-increasing order.
 *
 * Example 1:
 * Input: nums = [4,3,10,9,8]
 * Output: [10,9]
 * Explanation: The subsequences [10,9] and [10,8] are minimal such that the sum of their elements is strictly
 * greater than the sum of elements not included, however, the subsequence [10,9] has the maximum total sum of its elements.
 *
 * Example 2:
 * Input: nums = [4,4,7,6,7]
 * Output: [7,7,6]
 * Explanation: The subsequence [7,7] has the sum of its elements equal to 14 which is not strictly greater than the
 * sum of elements not included (14 = 4 + 4 + 6). Therefore, the subsequence [7,6,7] is the minimal satisfying the conditions.
 * Note the subsequence has to returned in non-decreasing order.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 500
 * 2. 1 <= nums[i] <= 100
 */
public class MinimumSubsequenceInNonIncreasingOrder {
    public static void main(String[] args) {
        System.out.println(minSubsequence(new int[]{4, 3, 10, 9, 8}));
    }

    /**
     * 从数组中抽取最少的数字，使得这部分 数字的和 比剩下 所有的数字和 更大
     * 对 nums 进行排序，从最大的优先选起，直到选择的元素之和超过总和的一半
     */
    public static List<Integer> minSubsequence(int[] nums) {
        List<Integer> result = new LinkedList<>();
        int sum = Arrays.stream(nums).sum();
        Arrays.parallelSort(nums);

        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            result.add(nums[i]);
            count += nums[i];
            if (count > sum / 2) {
                return result;
            }
        }
        return result;
    }
}
