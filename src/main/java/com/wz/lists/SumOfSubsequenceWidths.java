package com.wz.lists;

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
        int[] A = new int[]{2, 1, 3};
        System.out.println(sumSubseqWidths(A));
    }

    /**
     * 相当于把每个子序列的最大值加起来，把最小值减掉
     * 而第i大的数字作为最大值的组合有2^i种，第i大的数字作为最小值的组合有2^(n-1-i)种
     * 提示了结果可能是个超大数，要对 1e9+7 取余，虽然每次会对 1e9+7 取余，但是不能保证不会在取余之前就已经整型溢出，
     * 所以要定义 result 为长整型。其次，不能直接算 2^i 和 2^(n-1-i)，很容易溢出，即便是长整型，也有可能溢出。
     * 解决方案是，在累加i的同时，每次都乘以2，那么遍历到i的时候，也就乘到 2^i 了，这里每次乘2之后就立马对 1e9+7 取余
     */
    public static int sumSubseqWidths(int[] A) {
        Arrays.parallelSort(A);
        int n = A.length;
        long c = 1, result = 0, m = (long) (1e9 + 7);
        for (int i = 0; i < n; i++, c = (c << 1) % (m)) {
            result = (result + A[i] * c - A[n - 1 - i] * c) % m;
        }
        return (int) result;
    }
}
