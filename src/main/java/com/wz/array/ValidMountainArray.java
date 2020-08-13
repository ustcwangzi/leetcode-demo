package com.wz.array;

/**
 * Given an array A of integers, return true if and only if it is a valid mountain array.
 * Recall that A is a mountain array if and only if:
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * Example 1:
 * Input: [2,1]
 * Output: false
 *
 * Example 2:
 * Input: [3,5,5]
 * Output: false
 *
 * Example 3:
 * Input: [0,3,2,1]
 * Output: true
 *
 * Example 4:
 * Input: [3,2,1]
 * Output: false
 */
public class ValidMountainArray {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 1};
        System.out.println(validMountainArray(A));

        A = new int[]{3, 2, 1};
        System.out.println(validMountainArray(A));

        A = new int[]{1, 2, 3};
        System.out.println(validMountainArray(A));

        A = new int[]{0, 3, 3, 2, 1};
        System.out.println(validMountainArray(A));
    }

    /**
     * 山脉数组一定要有一个最高值，然后要同时有上坡和下坡。
     * 从左边开始依次比较两个数字，如果是上坡，继续寻找，直到遇到下坡停止，这样就找到了最高点，从右边也是同样的操作。
     * 然后排除只有上坡的，或者只有下坡的情况。
     * 最后比较一下，左边找到的最高点和右边找到的最高点是否是同一个点
     */
    public static boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }

        int left = 0, right = A.length - 1;
        while (left + 1 < A.length && A[left] < A[left + 1]) {
            left++;
        }
        while (right - 1 >= 0 && A[right - 1] > A[right]) {
            right--;
        }

        if (left == A.length - 1 || right == 0) {
            return false;
        }
        return left == right;
    }
}
