package com.wz.array;

import java.util.Arrays;

/**
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 * You may return any answer array that satisfies this condition.
 *
 * Example 1:
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 *
 * Note:
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class SortArrayByParityII {
    public static void main(String[] args) {
        int[] A = new int[]{4, 7, 5, 2,};
        System.out.println(Arrays.toString(sortArrayByParityII(A)));
    }

    /**
     * 利用两个指针，在偶数位置上找到第一个奇数；在奇数位置上找到第一个偶数，然后进行交换
     */
    public static int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1, len = A.length;
        while (i < len && j < len) {
            while (i < len && A[i] % 2 == 0) {
                i += 2;
            }
            while (j < len && A[j] % 2 == 1) {
                j += 2;
            }

            if (i < len && j < len) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }

        return A;
    }
}
