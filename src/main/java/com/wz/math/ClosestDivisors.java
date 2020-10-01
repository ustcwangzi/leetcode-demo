package com.wz.math;

import java.util.Arrays;

/**
 * Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.
 * Return the two integers in any order.
 *
 * Example 1:
 * Input: num = 8
 * Output: [3,3]
 * Explanation: For num + 1 = 9, the closest divisors are 3 & 3, for num + 2 = 10, the closest divisors are 2 & 5, hence 3 & 3 is chosen.
 *
 * Example 2:
 * Input: num = 123
 * Output: [5,25]
 */
public class ClosestDivisors {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestDivisors(8)));
        System.out.println(Arrays.toString(closestDivisors(123)));
    }

    /**
     * 从 1 遍历到 sqrt(num)，找到最接近的两个因数
     */
    public static int[] closestDivisors(int num) {
        int[] result = new int[2];
        int sqrt = (int) Math.floor(Math.sqrt(num + 2));
        for (int i = sqrt; i >= 1; i--) {
            if ((num + 1) % i == 0) {
                result[0] = (num + 1) / i;
                result[1] = i;
                return result;
            }
            if ((num + 2) % i == 0) {
                result[0] = (num + 2) / i;
                result[1] = i;
                return result;
            }
        }
        return result;
    }
}
