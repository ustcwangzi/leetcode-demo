package com.wz.other;

/**
 * Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.
 * The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
 *
 * Example 1:
 * Input: nums = [2,5,6,9,10]
 * Output: 2
 * Explanation:
 * The smallest number in nums is 2.
 * The largest number in nums is 10.
 * The greatest common divisor of 2 and 10 is 2.
 *
 * Example 2:
 * Input: nums = [7,5,6,8,3]
 * Output: 1
 * Explanation:
 * The smallest number in nums is 3.
 * The largest number in nums is 8.
 * The greatest common divisor of 3 and 8 is 1.
 *
 * Constraints:
 * 1. 2 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 1000
 */
public class FindGreatestCommonDivisorOfArray {
    public static void main(String[] args) {
        System.out.println(findGCD(new int[]{2, 5, 6, 9, 10}));
        System.out.println(findGCD(new int[]{7, 5, 6, 8, 3}));
    }

    public static int findGCD(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return gcd(min, max);
    }

    /**
     * 最大公约数，辗转相除法
     */
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
