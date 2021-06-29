package com.wz.binarysearch;

import java.util.Arrays;

/**
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor,
 * divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 * It is guaranteed that there will be an answer.
 *
 * Example 1:
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 *
 * Example 2:
 * Input: nums = [44,22,33,11,1], threshold = 5
 * Output: 44
 *
 * Constraints:
 * 1. 1 <= nums.length <= 5 * 10^4
 * 2. 1 <= nums[i] <= 10^6
 * 3. nums.length <= threshold <= 10^6
 */
public class FindTheSmallestDivisorGivenThreshold {
    public static void main(String[] args) {
        System.out.println(smallestDivisor(new int[]{1, 2, 5, 9}, 6));
        System.out.println(smallestDivisor(new int[]{44, 22, 33, 11, 1}, 5));
    }

    /**
     * 二分查找，与 {@link KokoEatingBananas} 类似
     * divisor 的范围为 [1...max{nums}]
     * 使用二分查找来求解，对于每个 mid，求出余数之和与 threshold 进行比较，若大于，则说明 mid 太小，将 left 赋值为 mid+1
     * 否则说明符合要求，将 right 赋值为 mid，缩小范围
     */
    public static int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = Arrays.stream(nums).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (valid(nums, threshold, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean valid(int[] nums, int threshold, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor;
        }
        return sum <= threshold;
    }
}
