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
