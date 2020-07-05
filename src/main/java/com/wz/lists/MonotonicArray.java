package com.wz.lists;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j].
 * An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 * Return true if and only if the given array A is monotonic.
 *
 * Example 1:
 * Input: [1,2,2,3]
 * Output: true
 *
 * Example 2:
 * Input: [6,5,4,4]
 * Output: true
 *
 * Example 3:
 * Input: [1,3,2]
 * Output: false
 */
public class MonotonicArray {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 2, 3};
        System.out.println(isMonotonic(A));

        A = new int[]{6, 5, 4, 4};
        System.out.println(isMonotonic(A));
    }

    public static boolean isMonotonic(int[] A) {
        if (A.length <= 2) {
            return true;
        }

        boolean increase = A[A.length - 1] >= A[0];
        for (int i = 1; i < A.length; i++) {
            if (increase && A[i] < A[i - 1]) {
                return false;
            }
            if (!increase && A[i] > A[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
