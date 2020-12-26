package com.wz.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums and an integer target.
 * Return the maximum number of non-empty non-overlapping subarrays such that the sum of values in each subarray is equal to target.
 *
 * Example 1:
 * Input: nums = [1,1,1,1,1], target = 2
 * Output: 2
 * Explanation: There are 2 non-overlapping subarrays [1,1,1,1,1] with sum equals to target(2).
 *
 * Example 2:
 * Input: nums = [-1,3,5,1,4,2,-9], target = 6
 * Output: 2
 * Explanation: There are 3 subarrays with sum equal to 6.
 * ([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^4 <= nums[i] <= 10^4
 * 3. 0 <= target <= 10^6
 */
public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget {
    public static void main(String[] args) {
        System.out.println(maxNonOverlapping(new int[]{-1, 3, 5, 1, 4, 2, -9}, 6));
    }

    /**
     * 遍历数组，用 sum 记录当前累计的前缀和，用 set 存储之前的前缀和
     * 如果 sum == target 或 set 中存在 sum-target，说明找到了一个以 num 结尾的满足条件的子数组
     * 此时将 sum 置为 0，并清空 set，继续寻找下一个子数组，因为题目要求不能重叠
     * 否则，就将 sum 存到 set 中，继续遍历下一个元素
     */
    public static int maxNonOverlapping(int[] nums, int target) {
        int sum = 0, result = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            sum += num;
            if (set.contains(sum - target) || sum == target) {
                result++;
                set.clear();
                sum = 0;
            } else {
                set.add(sum);
            }
        }
        return result;
    }

}
