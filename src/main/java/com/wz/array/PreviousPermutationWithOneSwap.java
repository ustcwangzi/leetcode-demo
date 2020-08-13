package com.wz.array;

import java.util.Arrays;

/**
 * Given an array A of positive integers (not necessarily distinct), return the lexicographically largest permutation
 * that is smaller than A, that can be made with one swap (A swap exchanges the positions of two numbers A[i] and A[j]).
 * If it cannot be done, then return the same array.
 *
 * Example 1:
 * Input: [3,2,1]
 * Output: [3,1,2]
 * Explanation: Swapping 2 and 1.
 *
 * Example 2:
 * Input: [1,1,5]
 * Output: [1,1,5]
 * Explanation: This is already the smallest permutation.
 *
 * Example 3:
 * Input: [1,9,4,6,7]
 * Output: [1,7,4,6,9]
 * Explanation: Swapping 9 and 7.
 *
 * Example 4:
 * Input: [3,1,1,3]
 * Output: [1,3,1,3]
 * Explanation: Swapping 1 and 3.
 */
public class PreviousPermutationWithOneSwap {
    public static void main(String[] args) {
        int[] A = new int[]{3, 2, 1};
        System.out.println(Arrays.toString(prevPermOpt1(A)));

        A = new int[]{1, 1, 5};
        System.out.println(Arrays.toString(prevPermOpt1(A)));

        A = new int[]{1, 9, 4, 6, 7};
        System.out.println(Arrays.toString(prevPermOpt1(A)));

        A = new int[]{3, 1, 1, 3};
        System.out.println(Arrays.toString(prevPermOpt1(A)));
    }

    /**
     * 如果从右到左一直是降序，则直接返回原数组
     * 从右到左找到首个升序的数A，然后找到比A小的数中“最靠右、相等相邻数中最靠左”的数B，交换AB
     */
    public static int[] prevPermOpt1(int[] A) {
        int left;
        // 找到首个升序的数
        for (left = A.length - 1; left > 0; left--) {
            if (A[left - 1] > A[left]) {
                break;
            }
        }
        if (left == 0) {
            return A;
        }

        left--;
        int right = A.length - 1;
        while (A[right] >= A[left] || A[right] == A[right - 1]) {
            --right;
        }
        swap(A, left, right);
        return A;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
