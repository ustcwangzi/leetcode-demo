package com.wz.math;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
 *
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 */
public class PowXN {
    public static void main(String[] args) {
        System.out.println(myPow1(2, 10));
        System.out.println(myPow2(2, 10));
        System.out.println(myPow3(2, -2147483648));
    }

    public static double myPow1(double x, int n) {
        return Math.pow(x, n);
    }

    /**
     * 如果 n 是偶数，比如 4，那么相当于是两个2相乘，也就是 n^4 = n^2 * n^2，也就是说现在只需要求出 n^2 就可以了
     * 如果 n 为奇数的话也是差不多的，比如说 5，x^5 = x^2 * x^2 * x，也就说还是求出 x^2，比上面多乘了一个x
     */
    public static double myPow2(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double d = myPow2(x * x, n / 2);
        if (n % 2 == 1) {
            d *= x;
        } else if (n % 2 == -1) {
            d /= x;
        }
        return d;
    }

    public static double myPow3(double x, int n) {
        double result = 1.0;
        long power = n;
        // 以免负数溢出
        if (n < 0) {
            power = -1 * power;
        }
        while (power > 0) {
            if (power % 2 == 0) {
                x *= x;
                power /= 2;
            } else {
                result = result * x;
                power--;
            }
        }
        return n > 0 ? result : 1.0 / result;
    }
}
