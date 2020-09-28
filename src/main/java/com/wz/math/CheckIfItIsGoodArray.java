package com.wz.math;

/**
 * Given an array nums of positive integers. Your task is to select some subset of nums,
 * multiply each element by an integer and add all these numbers.
 * The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.
 * Return True if the array is good otherwise return False.
 *
 * Example 1:
 * Input: nums = [12,5,7,23]
 * Output: true
 * Explanation: Pick numbers 5 and 7.
 * 5*3 + 7*(-2) = 1
 *
 * Example 2:
 * Input: nums = [29,6,10]
 * Output: true
 * Explanation: Pick numbers 29, 6 and 10.
 * 29*1 + 6*(-3) + 10*(-1) = 1
 */
public class CheckIfItIsGoodArray {
    public static void main(String[] args) {
        System.out.println(isGoodArray(new int[]{12, 5, 7, 23}));
        System.out.println(isGoodArray(new int[]{29, 6, 10}));
    }

    /**
     * 裴蜀定理
     * 求出所有元素的最大公约数，判断是否为1即可
     */
    public static boolean isGoodArray(int[] nums) {
        int x = nums[0];
        for (int num : nums) {
            x = gcd(x, num);
        }
        return x == 1;
    }

    private static int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
