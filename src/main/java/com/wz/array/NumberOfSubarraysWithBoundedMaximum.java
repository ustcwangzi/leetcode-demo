package com.wz.array;

/**
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 *
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 */
public class NumberOfSubarraysWithBoundedMaximum {
    public static void main(String[] args) {
        int[] A = new int[]{2, 1, 4, 3};
        System.out.println(numSubarrayBoundedMax(A, 2, 3));
    }

    /**
     * 定义[left, right]表示符合条件的合法区间，遍历数组A，根据A[right]的大小，可以分为三种情况进行处理：
     * 1）A[right]在[L, R]区间内，此时有从[left, right]到[right, right]一共(right - left + 1)个合法的区间，
     *    更新 result += (right - left + 1)，并且更新count的值（后面会有用）；
     * 2）A[right] < L，此时虽然A[right]不在[L, R]区间内，但是对于前面的count个合法区间，每个加上A[right]，
     *    仍然可以形成合法的区间，所以 result += count；
     * 3）A[right] > R，此时所有包含A[right]的区间都将不再合法，所以更新左边界left = right + 1，并更新count = 0
     */
    public static int numSubarrayBoundedMax(int[] A, int L, int R) {
        int left = 0, count = 0, result = 0;
        for (int right = 0; right < A.length; right++) {
            int cur = A[right];
            if (cur >= L && cur <= R) {
                count = right - left + 1;
                result += count;
            } else if (cur < L) {
                result += count;
            } else {
                left = right + 1;
                count = 0;
            }
        }

        return result;
    }
}
