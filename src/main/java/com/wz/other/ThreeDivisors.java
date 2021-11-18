package com.wz.other;

/**
 * Given an integer n, return true if n has exactly three positive divisors. Otherwise, return false.
 * An integer m is a divisor of n if there exists an integer k such that n = k * m.
 *
 * Example 1:
 * Input: n = 2
 * Output: false
 * Explantion: 2 has only two divisors: 1 and 2.
 *
 * Example 2:
 * Input: n = 4
 * Output: true
 * Explantion: 4 has three divisors: 1, 2, and 4.
 *
 * Constraints:
 * 1 <= n <= 10^4
 */
public class ThreeDivisors {
    public static void main(String[] args) {
        System.out.println(isThree1(4));
        System.out.println(isThree2(4));
    }

    /**
     * 直接从 1 遍历到 n，能被 n 整除时 count++，最后判断 count 是否为 3
     */
    public static boolean isThree1(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += n % i == 0 ? 1 : 0;
        }
        return count == 3;
    }

    /**
     * 从 1 遍历到 n，能被 sqrt(n)，不能整除直接跳过
     * 能整除且结果是 i 时 count++，否则 count+2，最后判断 count 是否为 3
     */
    public static boolean isThree2(int n) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i != 0) {
                continue;
            }
            if (n / i == i) {
                // 如 4 / 2 == 2，只能算作一次
                count++;
            } else {
                // 如 4 / 1 == 4，可以算作两次
                count += 2;
            }
        }
        return count == 3;
    }
}
