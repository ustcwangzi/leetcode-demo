package com.wz.other;

/**
 * A digit string is good if the digits (0-indexed) at even indices are even and the digits at odd indices are prime (2, 3, 5, or 7).
 * For example, "2582" is good because the digits (2 and 8) at even positions are even and the digits (5 and 2) at odd positions are prime.
 * However, "3245" is not good because 3 is at an even index but is not even.
 * Given an integer n, return the total number of good digit strings of length n. Since the answer may be large, return it modulo 109 + 7.
 * A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
 *
 * Example 1:
 * Input: n = 1
 * Output: 5
 * Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".
 *
 * Example 2:
 * Input: n = 50
 * Output: 564908303
 *
 * Constraints:
 * 1 <= n <= 10^15
 */
public class CountGoodNumbers {
    public static void main(String[] args) {
        System.out.println(countGoodNumbers(1));
        System.out.println(countGoodNumbers(50));
    }

    private static final int MOD = 1_000_000_007;

    /**
     * 在长度为 n 的字符串中计算每个位置上可能的数字对应的排序数目即可
     * 对于下标为偶数的位置总共有5种选择，分别为 0，2，4，6，8，对于奇数下标有四种选择，分别为 2，3，5，7
     * 长度为n的偶数下标位置有 n/2 向上取整个，奇数下标位置为 n/2 向下取整个，向上取整可以使用 (n+1)/2
     * 分为奇数下标与偶数下标计算排列数目即可，所以长度为n的字符串总共为 5^((n+1)/2) + 4^(n/2)
     * 计算幂次时可以参考 {@link com.wz.math.PowXN}
     */
    public static int countGoodNumbers(long n) {
        return (int) (myPow(5L, (n + 1) / 2) * myPow(4L, n / 2) % MOD);
    }

    private static long myPow(long x, long n) {
        long result = 1L;
        while (n > 0) {
            if (n % 2 != 0) {
                result = result * x % MOD;
            }
            n /= 2;
            x = x * x % MOD;
        }
        return result;
    }
}
