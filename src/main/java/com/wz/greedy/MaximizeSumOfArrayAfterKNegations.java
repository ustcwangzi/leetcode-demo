package com.wz.greedy;

import java.util.Arrays;

/**
 * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i],
 * and we repeat this process K times in total.  (We may choose the same index i multiple times.)
 * Return the largest possible sum of the array after modifying it in this way.
 *
 * Example 1:
 * Input: A = [4,2,3], K = 1
 * Output: 5
 * Explanation: Choose indices (1,) and A becomes [4,-2,3].
 *
 * Example 2:
 * Input: A = [3,-1,0,2], K = 3
 * Output: 6
 * Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
 *
 * Example 3:
 * Input: A = [2,-3,-1,5,-4], K = 2
 * Output: 13
 * Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
 *
 * Note:
 * 1. 1 <= A.length <= 10000
 * 2. 1 <= K <= 10000
 * 3. -100 <= A[i] <= 100
 */
public class MaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        System.out.println(largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        System.out.println(largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
    }

    /**
     * 优先修改数组中的负数，修改完成后，若 K 是偶数，则直接返回数组总和即可，因为允许多次选择同一个位置
     * 否则，需要修改数组中绝对值最小的那个数，最后返回数组总和
     */
    public static int largestSumAfterKNegations(int[] A, int K) {
        Arrays.parallelSort(A);
        // 仅修改负数
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0 || K <= 0) {
                break;
            }
            A[i] = -A[i];
            K--;
        }

        // 找到并修改绝对值最小的数
        if (K % 2 == 1) {
            int index = 0, min = Math.abs(A[0]);
            for (int i = 1; i < A.length; i++) {
                if (Math.abs(A[i]) < min) {
                    min = Math.abs(A[i]);
                    index = i;
                }
            }
            A[index] = -A[index];
        }

        return Arrays.stream(A).sum();
    }
}
