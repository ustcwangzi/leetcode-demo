package com.wz.lists;

import java.util.Arrays;

/**
 * Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 * Return the least number of moves to make every value in A unique.
 *
 * Example 1:
 * Input: [1,2,2]
 * Output: 1
 * Explanation:  After 1 move, the array could be [1, 2, 3].
 *
 * Example 2:
 * Input: [3,2,1,2,1,7]
 * Output: 6
 * Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 */
public class MinimumIncrementToMakeArrayUnique {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 2};
        System.out.println(minIncrementForUnique(A));
        A = new int[]{3, 2, 1, 2, 1, 7};
        System.out.println(minIncrementForUnique(A));
    }

    /**
     * 排序后，相同的数排列在一起，A[i] 至少应该是前面数+1, 因此 cur = Math.max(A[i-1] + 1, A[i])，
     * A[i] 应该增加到 cur 这个数字，可以直接求出它需要扩大的步数 cur - A[i]
     */
    public static int minIncrementForUnique(int[] A) {
        if (A.length <= 1) {
            return 0;
        }

        Arrays.parallelSort(A);
        int step = 0, cur;
        for (int i = 1; i < A.length; i++) {
            cur = Math.max(A[i - 1] + 1, A[i]);
            step += cur - A[i];
            A[i] = cur;
        }

        return step;
    }
}
