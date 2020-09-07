package com.wz.math;

/**
 * Given a positive integer n and you can do operations as follow:
 * If n is even, replace n with n/2.
 * If n is odd, you can replace n with either n + 1 or n - 1.
 * What is the minimum number of replacements needed for n to become 1?
 *
 * Example 1:
 * Input:
 * 8
 * Output:
 * 3
 * Explanation:
 * 8 -> 4 -> 2 -> 1
 *
 * Example 2:
 * Input:
 * 7
 * Output:
 * 4
 * Explanation:
 * 7 -> 8 -> 4 -> 2 -> 1
 * or
 * 7 -> 6 -> 3 -> 2 -> 1
 */
public class IntegerReplacement {
    public static void main(String[] args) {
        System.out.println(integerReplacement(8));
        System.out.println(integerReplacement(7));
    }

    /**
     * 直接按照规则写出递归即可，由于有 n+1 的操作，就有可能溢出，所以可以先将 n 转为 long，然后再进行运算
     */
    public static int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement(n / 2);
        }
        return 2 + Math.min(integerReplacement((int) (((long) n + 1) / 2)), integerReplacement((n - 1) / 2));
    }
}
