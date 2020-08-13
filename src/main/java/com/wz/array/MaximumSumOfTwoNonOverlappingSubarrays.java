package com.wz.array;

/**
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays,
 * which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 *
 * Example 1:
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 *
 * Example 2:
 * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 * Output: 29
 * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 *
 * Example 3:
 * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 *
 * Output: 31
 * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 */
public class MaximumSumOfTwoNonOverlappingSubarrays {
    public static void main(String[] args) {
        int[] A = new int[]{0, 6, 5, 2, 2, 5, 1, 9, 4};
        System.out.println(maxSumTwoNoOverlap(A, 1, 2));

        A = new int[]{3, 8, 1, 3, 2, 1, 8, 9, 0};
        System.out.println(maxSumTwoNoOverlap(A, 3, 2));

        A = new int[]{2, 1, 5, 6, 0, 9, 5, 0, 3, 8};
        System.out.println(maxSumTwoNoOverlap(A, 4, 3));
    }

    /**
     * 动态规划
     * L和M的关系可能为以下两种：[..L..M..] 和 [..M..L..]
     * 可以固定右边的子数组M(或L)，求左边的子数组最大和 LMax(或MMax)
     * 对每一组 LMax+M，求最大和，再向右移动M，同样，对每一组 MMax+L，求最大和，再向右移动L
     *
     * 凡是要求数组某一段的和，要想到用pre_sum，pre_sum[i]表示i之前的元素之和，
     * A[i]到A[j]之间所有元素之和就是 pre_sum[j] - pre_sum[i - 1]
     *
     * LMax 表示 i-M 之前的最大连续L个元素之和，LMax = max(LMax, pre_sum[i-M] - pre_sum[i-M-L])
     * LMax + pre_sum[i] - pre_sum[i-M] 表示以i结尾最后连续M个元素，与之前最大的连续L个元素之和；
     * 同理，MMax 表示 i-L 之前的最大连续M个元素之和，MMax = max(MMax, pre_sum[i-L] - pre_sum[i-L-M])
     * MMax + pre_sum[i] - pre_sum[i-L] 表示以i结尾最后连续L个元素，与之前最大的连续M个数之和。
     * 取其中较大的与最终要返回的值 result 比较，并更新即可
     */
    public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i - 1];
        }
        int result = A[L + M - 1], LMax = A[L - 1], MMax = A[M - 1];
        for (int i = L + M; i < A.length; i++) {
            // i-M 之前的最大连续L个元素之和, [..L..M..]
            LMax = Math.max(LMax, A[i - M] - A[i - M - L]);
            // i-L 之前的最大连续M个元素之和, [..M..L..]
            MMax = Math.max(MMax, A[i - L] - A[i - L - M]);
            result = Math.max(result, Math.max(LMax + A[i] - A[i - M], MMax + A[i] - A[i - L]));
        }

        return result;
    }
}
