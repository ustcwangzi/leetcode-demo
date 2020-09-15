package com.wz.math;

/**
 * Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: 5 = 5 = 2 + 3
 *
 * Example 2:
 * Input: 9
 * Output: 3
 * Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 *
 * Example 3:
 * Input: 15
 * Output: 4
 * Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 * Note: 1 <= N <= 10 ^ 9.
 */
public class ConsecutiveNumbersSum {
    public static void main(String[] args) {
        System.out.println(consecutiveNumbersSum(5));
        System.out.println(consecutiveNumbersSum(9));
        System.out.println(consecutiveNumbersSum(15));
    }

    /**
     * 连续自然数的和其实就是一个公差为1的等差数列的和，设这个数列是 x,x+1,x+2,...,x+n，那么这个数列的和是 (n+1)*x+n*(n+1)/2，
     * 这个数列的长度是 n+1，第一个数字是x，然后就可以从数字长度为1开始逐渐尝试，看N能否分解成为长度为 n 的等差数列的和，
     * 如果可以，那么一定可以求得整数解 x 使得 x=(N-n*(n+1))/(n+1)
     */
    public static int consecutiveNumbersSum(int N) {
        int result = 0, n = 0;
        while (true) {
            int top = N - (n * n + n) / 2;
            if (top <= 0)
                break;
            if (top % (n + 1) == 0) {
                result++;
            }
            n++;
        }
        return result;
    }
}
