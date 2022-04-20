package com.wz.other;

/**
 * Given an integer array nums of size n, return the number with the value closest to 0 in nums.
 * If there are multiple answers, return the number with the largest value.
 *
 * Example 1:
 * Input: nums = [-4,-2,1,4,8]
 * Output: 1
 * Explanation:
 * The distance from -4 to 0 is |-4| = 4.
 * The distance from -2 to 0 is |-2| = 2.
 * The distance from 1 to 0 is |1| = 1.
 * The distance from 4 to 0 is |4| = 4.
 * The distance from 8 to 0 is |8| = 8.
 * Thus, the closest number to 0 in the array is 1.
 *
 * Example 2:
 * Input: nums = [2,-1,1]
 * Output: 1
 * Explanation: 1 and -1 are both the closest numbers to 0, so 1 being larger is returned.
 *
 * Constraints:
 * 1. 1 <= n <= 1000
 * 2. -10^5 <= nums[i] <= 10^5
 */
public class FindClosestNumberToZero {
    public static void main(String[] args) {
        System.out.println(findClosestNumber(new int[]{-4, -2, 1, 4, 8}));
        System.out.println(findClosestNumber(new int[]{2, -1, 1}));
    }

    /**
     * 遍历数组，将 |num| 与 |result| 进行比较
     * - 若 |num| 小，则更新 result 为 num
     * - 若相等，则更新 result 为 max{num, result}
     */
    public static int findClosestNumber(int[] nums) {
        int result = nums[0];
        for (int num : nums) {
            if (Math.abs(num) < Math.abs(result)) {
                result = num;
            } else if (Math.abs(num) == Math.abs(result)) {
                result = Math.max(num, result);
            }
        }
        return result;
    }
}
