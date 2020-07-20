package com.wz.lists;

/**
 * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
 * An array A is a zigzag array if either:
 * Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * Return the minimum number of moves to transform the given array nums into a zigzag array.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: We can decrease 2 to 0 or 3 to 1.
 *
 * Example 2:
 * Input: nums = [9,6,1,6,2]
 * Output: 4
 */
public class DecreaseElementsToMakeArrayZigzag {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(movesToMakeZigzag(nums));

        nums = new int[]{9, 6, 1, 6, 2};
        System.out.println(movesToMakeZigzag(nums));
    }

    /**
     * 两种情况，偶数为小或奇数为小，当前数需要减小到比左右两边的较小数小1
     */
    public static int movesToMakeZigzag(int[] nums) {
        int result1 = 0, result2 = 0;
        // 奇数为小, 从第一个数开始每个偶数都要小于左右两边的数
        for (int i = 0; i < nums.length; i += 2) {
            int miner = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                miner = Math.min(miner, nums[i - 1]);
            }
            if (i + 1 < nums.length) {
                miner = Math.min(miner, nums[i + 1]);
            }
            // 当前元素需要减小到比 min(left, right) 小1
            if (nums[i] >= miner) {
                result1 += nums[i] - miner + 1;
            }
        }

        // 偶数为大, 从第二个数开始每个奇数都要小于左右两边的数
        for (int i = 1; i < nums.length; i += 2) {
            int miner = nums[i - 1];
            if (i + 1 < nums.length) {
                miner = Math.min(miner, nums[i + 1]);
            }
            if (nums[i] >= miner) {
                result2 += nums[i] - miner + 1;
            }
        }

        return Math.min(result1, result2);
    }
}
