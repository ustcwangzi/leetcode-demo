package com.wz.twopointer;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. nums[i] is either 0 or 1.
 * 3. 0 <= k <= nums.length
 */
public class MaxConsecutiveOnesIII {
    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    /**
     * 双指针
     * 统计 nums[left,right] 中 0 和 1 的个数，若 0 的个数超过 k 了，则需要将 left 右移，同时更新 0 和 1 的个数
     * 直到 0 的个数不超过 k，此时的 0 都可以转为 1，因此使用 countOne + countZero 来更新结果
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0, result = 0, countZero = 0, countOne = 0;
        for (int num : nums) {
            if (num == 0) {
                countZero++;
            } else {
                countOne++;
            }

            while (countZero > k) {
                if (nums[left] == 0) {
                    countZero--;
                } else {
                    countOne--;
                }
                left++;
            }

            result = Math.max(result, countOne + countZero);
        }
        return result;
    }
}
