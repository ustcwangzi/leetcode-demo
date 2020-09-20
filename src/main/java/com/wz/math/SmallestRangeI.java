package com.wz.math;

/**
 * Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].
 * After this process, we have some array B.
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 *
 * Example 1:
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 *
 * Example 2:
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 */
public class SmallestRangeI {
    public static void main(String[] args) {
        System.out.println(smallestRangeI(new int[]{1}, 0));
        System.out.println(smallestRangeI(new int[]{0, 10}, 2));
    }

    /**
     * 数组中的每一个数字都可以加上 [-K, K] 范围内的任意一个数字，问新数组的最大值最小值之间的差值最小是多少
     * 如何让二者之间的差值最小呢？当然是让最大值尽可能变小，最小值尽可能变大了，所以最大值 mx 要加上 -K，而最小值 mn 要加上K，
     * 然后再做减法，即 (mx-K)-(mn+K) = mx-mn+2K
     */
    public static int smallestRangeI(int[] A, int K) {
        int mx = A[0], mn = A[0];
        for (int num : A) {
            mx = Math.max(mx, num);
            mn = Math.min(mn, num);
        }
        return Math.max(0, mx - mn - 2 * K);
    }
}
