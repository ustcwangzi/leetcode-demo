package com.wz.math;

import java.util.Arrays;

/**
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
 * After this process, we have some array B.
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 *
 * Example 1:
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 *
 * Example 2:
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 */
public class SmallestRangeII {
    public static void main(String[] args) {
        System.out.println(smallestRangeII(new int[]{1}, 0));
        System.out.println(smallestRangeII(new int[]{0, 10}, 2));
    }

    /**
     * 每个数字必须加上K或者—K，问新数组的最大值最小值之间的差值最小是多少
     * 如果不考虑数字修改，那么原数组的最大值最小值之间的差值就是所求，这里可以当作结果 right 的初始值。
     * 由于每个数字都需要加K或者减K，为了使得新数组的最大值最小值的差值最小，应该尽量使原数组中的较小的数字加K，较大的数字减K，
     * 所以最好是先给原数组排个序，然后在数组的某个位置i为界限，将原数组分为两段，前面所有的数字都加K，后面所有的数字都减K。
     * 则前半段 [0, i] 中的最大值是 A[i]+K，最小值是 A[0]+K，后半段 [i+1, n-1] 范围内的最大值是 A[n-1]-K，最小值是 A[i+1]-K，
     * 所以整个数组的最大值是 A[i]+K 和 A[n-1]-K 中的较大值，最小值是 A[0]+K 和 A[i+1]-K 中的较小值，二者做差就是可能的结果了
     */
    public static int smallestRangeII(int[] A, int K) {
        Arrays.parallelSort(A);
        int n = A.length, result = A[n - 1] - A[0];
        int left = A[0] + K, right = A[n - 1] - K;

        for (int i = 0; i < n - 1; ++i) {
            int high = Math.max(right, A[i] + K);
            int low = Math.min(left, A[i + 1] - K);
            result = Math.min(result, high - low);
        }
        return result;
    }
}
