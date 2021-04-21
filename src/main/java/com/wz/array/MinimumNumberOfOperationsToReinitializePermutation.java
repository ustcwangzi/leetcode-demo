package com.wz.array;

/**
 * You are given an even integer n. You initially have a permutation perm of size n where perm[i] == i (0-indexed).
 * In one operation, you will create a new array arr, and for each i:
 * 1. If i % 2 == 0, then arr[i] = perm[i / 2].
 * 2. If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
 * You will then assign arr to perm.
 * Return the minimum non-zero number of operations you need to perform on perm to return the permutation to its initial value.
 *
 * Example 1:
 * Input: n = 2
 * Output: 1
 * Explanation: perm = [0,1] initially.
 * After the 1st operation, perm = [0,1]
 * So it takes only 1 operation.
 *
 * Example 2:
 * Input: n = 4
 * Output: 2
 * Explanation: perm = [0,1,2,3] initially.
 * After the 1st operation, perm = [0,2,1,3]
 * After the 2nd operation, perm = [0,1,2,3]
 * So it takes only 2 operations.
 *
 * Constraints:
 * 1. 2 <= n <= 1000
 * 2. n is even.
 */
public class MinimumNumberOfOperationsToReinitializePermutation {
    public static void main(String[] args) {
        System.out.println(reinitializePermutation(2));
        System.out.println(reinitializePermutation(4));
    }

    /**
     * 当元素 1 回到初始位置时，所有元素的排列也会同时归位，因此直接分析元素 1 的变换过程即可
     */
    public static int reinitializePermutation(int n) {
        int i = 1, step = 0;
        while (step == 0 || i != 1) {
            i = transform(i, n);
            step++;
        }
        return step;
    }

    private static int transform(int i, int n) {
        if (i % 2 == 0) {
            return i / 2;
        }
        return n / 2 + (i - 1) / 2;
    }
}
