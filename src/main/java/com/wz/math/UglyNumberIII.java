package com.wz.math;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive integers which are divisible by a or b or c.
 *
 * Example 1:
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 *
 * Example 2:
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
 */
public class UglyNumberIII {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(3, 2, 3, 5));
        System.out.println(nthUglyNumber(4, 2, 3, 4));
    }

    /**
     * 怎么求出在[1,x]区间内有几个丑数？只要（x/a）+（x/b）+（x/c）即可，但是可能会有重复的值，比如a=2，b=3时，丑数6就会多计算一次，
     * 所以还需要减去(x/lcm(a,b) + x/lcm(c,b) + x/lcm(a,c))。这里lcm(a,b)表示a和b的最小公倍数。
     * 还有lcm(a,b,c)的情况，因为前面求两两最小公倍数的时候多减了一次，所以这里要再加上 x/lcm(a,b,c) ，就可以得到x前面有几个丑数
     * 由于丑数的数量随着x的增加而增加，所以用二分查找的方法很容易就可以求得指定位置的丑数
     */
    public static int nthUglyNumber(int n, int a, int b, int c) {
        int left = 1, right = Integer.MAX_VALUE;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (isUgly(middle, a, b, c, n)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    private static boolean isUgly(long middle, long a, long b, long c, long n) {
        return (int) (middle / a + middle / b + middle / c - middle / lcm(a, b) - middle / lcm(b, c) - middle / lcm(c, a) + middle / lcm(a, lcm(b, c))) >= n;
    }

    private static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    private static long lcm(long a, long b) {
        return a * b / (gcd(a, b));
    }
}
