package com.wz.math;

import java.util.Arrays;

/**
 * Given an array of integers A, consider all non-empty subsequences of A.
 * For any sequence S, let the width of S be the difference between the maximum and minimum element of S.
 * Return the sum of the widths of all subsequences of A.
 * As the answer may be very large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 * Input: [2,1,3]
 * Output: 6
 * Explanation:
 * Subsequences are [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3].
 * The corresponding widths are 0, 0, 0, 1, 1, 2, 2.
 * The sum of these widths is 6.
 */
public class SumOfSubsequenceWidths {
    public static void main(String[] args) {
        System.out.println(sumSubseqWidths(new int[]{2, 1, 3}));
    }

    /**
     * 一个长度为n的数组，共有 2^n 个序列。那么在给数组排序之后，对于其中任意一个数字 A[i]，其前面共有i个数是小于等于 A[i] 的，
     * 这i个数字共有 2^i 个子序列，它们加上 A[i] 都可以组成一个新的非空子序列，并且 A[i] 是这里面最大的数字，那么在宽度计算的时候，
     * 就要加上 A[i] x (2^i)，同理，A[i] 后面还有 n-1-i 个数字是大于等于它的，后面可以形成 2^(n-1-i) 个子序列，
     * 每个加上 A[i] 就都是一个新的非空子序列，同时 A[i] 是这些子序列中最小的一个，那么结果中就要减去 A[i] x (2 ^ (n-1-i))。
     * 对于每个数字都这么计算一下，就是最终要求的所有子序列的宽度之和了。
     * 可能你会怀疑虽然加上了 A[i] 前面 2^i 个子序列的最大值，那些子序列的最小值减去了么？其实是减去了的，虽然不是在遍历 A[i] 的时候减去，
     * 在遍历之前的数字时已经将所有该数字是子序列最小值的情况减去了，
     * 同理，A[i] 后面的那些 2^(n-1-i) 个子序列的最大值也是在遍历到的时候才加上的，所以不会漏掉任何一个数字
     *  2^i 和 2^(n-1-i) 不方便同时计算，将 A[i] x (2^(n-1-i)) 转换为 A[n-1-i] x 2^i
     */
    public static int sumSubseqWidths(int[] A) {
        long res = 0, n = A.length, M = (long) (1e9 + 7), c = 1;
        Arrays.parallelSort(A);
        for (int i = 0; i < n; ++i) {
            res = (res + A[i] * c - A[(int) (n - i - 1)] * c) % M;
            c = (c << 1) % M;
        }
        return (int) res;
    }
}
