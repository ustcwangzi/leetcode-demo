package com.wz.math;

/**
 * Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.
 * Return the length of N.  If there is no such N, return -1.
 *
 * Example 1:
 * Input: 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 *
 * Example 2:
 * Input: 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 */
public class SmallestIntegerDivisibleByK {
    public static void main(String[] args) {
        System.out.println(smallestRepunitDivByK(1));
        System.out.println(smallestRepunitDivByK(2));
    }

    /**
     * 偶数乘以任何数都是偶数结尾，所以不会以1结尾。同理5乘以任何数要么是0要么是5. 不会以1结尾
     * 枚举 N 从1,11,111,... 为了防止整数溢出，运用余数定理
     * n的变化公式为
     * 1. 初始状态 n = 0
     * 2. 递推公式 n = 10 * n + 1
     */
    public static int smallestRepunitDivByK(int K) {
        if (K % 2 == 0 || K % 5 == 0) {
            return -1;
        }

        int n = 0, step = 1;
        while (true) {
            n = n * 10 + 1;
            int tmp = n % K;
            if (tmp == 0) {
                return step;
            }
            step++;
            n = tmp;
        }
    }
}
