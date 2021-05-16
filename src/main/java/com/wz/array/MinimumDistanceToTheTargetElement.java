package com.wz.array;

/**
 * Given an integer array nums (0-indexed) and two integers target and start, find an index i such that
 * nums[i] == target and abs(i - start) is minimized. Note that abs(x) is the absolute value of x.
 * Return abs(i - start).
 * It is guaranteed that target exists in nums.
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5], target = 5, start = 3
 * Output: 1
 * Explanation: nums[4] = 5 is the only value equal to target, so the answer is abs(4 - 3) = 1.
 *
 * Example 2:
 * Input: nums = [1,1,1,1,1,1,1,1,1,1], target = 1, start = 0
 * Output: 0
 * Explanation: Every value of nums is 1, but nums[0] minimizes abs(i - start), which is abs(0 - 0) = 0.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 104
 * 3. 0 <= start < nums.length
 * 4. target is in nums.
 */
public class MinimumDistanceToTheTargetElement {
    public static void main(String[] args) {
        System.out.println(getMinDistance(new int[]{1, 2, 3, 4, 5}, 5, 3));
        System.out.println(getMinDistance(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 1, 0));
    }

    /**
     * 直接遍历数组，当前值等于 target 时更新结果即可
     */
    public static int getMinDistance(int[] nums, int target, int start) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result = Math.min(result, Math.abs(start - i));
            }
        }
        return result;
    }
}
