package com.wz.greedy;

/**
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 * Return the maximum length of a subarray with positive product.
 *
 * Example 1:
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 *
 * Example 2:
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
 *
 * Example 3:
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^9 <= nums[i] <= 10^9
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {
    public static void main(String[] args) {
        System.out.println(getMaxLen(new int[]{-1, -2, -3, 0, 1}));
        System.out.println(getMaxLen(new int[]{0, 1, -2, -3, -4}));
    }

    /**
     * 统计负数的个数 negativeCount，如果是奇数个，就要减去第一个负数的 index，如果是偶数个，那么就减去 zeroIndex，
     * 如果遇见 0，前面的结果都需要清空，同时 zeroIndex 赋值为 i
     */
    public static int getMaxLen(int[] nums) {
        int result = 0, negativeCount = 0, firstNegativeIndex = -1, zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                negativeCount = 0;
                firstNegativeIndex = -1;
                zeroIndex = i;
                continue;
            }

            if (nums[i] < 0) {
                negativeCount++;
                // 第一个负数出现的位置
                if (firstNegativeIndex == -1) {
                    firstNegativeIndex = i;
                }
            }

            if (negativeCount % 2 == 0) {
                result = Math.max(result, i - zeroIndex);
            } else {
                result = Math.max(result, i - firstNegativeIndex);
            }
        }
        return result;
    }
}
