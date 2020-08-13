package com.wz.array;

/**
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubArraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, nums));
    }

    /**
     * 滑动窗口
     * 使用两个指针left和right，分别记录子数组的左右的边界位置，然后我们让right向右移，直到子数组和大于等于给定值或者right达到数组末尾
     * 此时我们更新最短距离，并且将left向右移一位，然后在sum中减去移去的值
     * 然后重复上面的步骤，直到right到达末尾，且left到达临界位置
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0, sum = 0, result = nums.length + 1;
        while (right < nums.length) {
            while (sum < s && right < nums.length) {
                sum += nums[right++];
            }
            while (sum >= s) {
                result = Math.min(result, right - left);
                sum -= nums[left++];
            }
        }
        return result == nums.length + 1 ? 0 : result;
    }
}
