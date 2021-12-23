package com.wz.other;

/**
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
 * 1. 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * 2. 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * 3. 0, if none of the previous conditions holds.
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 2.
 *
 * Example 2:
 * Input: nums = [2,4,6,4]
 * Output: 1
 * Explanation: For each index i in the range 1 <= i <= 2:
 * - The beauty of nums[1] equals 1.
 * - The beauty of nums[2] equals 0.
 *
 * Example 3:
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 0.
 *
 * Constraints:
 * 1. 3 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 */
public class SumOfBeautyInTheArray {
    public static void main(String[] args) {
        System.out.println(sumOfBeauties(new int[]{1, 2, 3}));
        System.out.println(sumOfBeauties(new int[]{2, 4, 6, 4}));
    }

    /**
     * 使用两个数组 left[]、right[] 分别记录当前元素 i 左侧的最大值 和 右侧的最小值
     * 然后 i 从 1 ～ n-1，若 nums[i] > left[i] && nums[i] < right[i] 说明满足条件一，结果加 2
     * 若 nums[i] > nums[i-1] && nums[i] < nums[i+1] 说明满足条件二，结果加 1
     */
    public static int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] left = new int[n], right = new int[n];
        right[n - 1] = Integer.MAX_VALUE;
        for (int i = 1, j = n - 2; i < n && j >= 0; i++, j--) {
            left[i] = Math.max(left[i - 1], nums[i - 1]);
            right[j] = Math.min(right[j + 1], nums[j + 1]);
        }

        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > left[i] && nums[i] < right[i]) {
                result += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                result += 1;
            }
        }
        return result;
    }
}
