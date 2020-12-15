package com.wz.dynamicprogramming;

/**
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 * Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 * Input: arr = [1,2], k = 3
 * Output: 9
 *
 * Example 2:
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 *
 * Example 3:
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= arr.length <= 10^5
 * 2. 1 <= k <= 10^5
 * 3. -10^4 <= arr[i] <= 10^4
 */
public class KConcatenationMaximumSum {
    public static void main(String[] args) {
        System.out.println(kConcatenationMaxSum(new int[]{1, -2, 1}, 5));
    }

    /**
     * 3cases
     * 1) if k==1 return kadane of array
     * 2) if sum > 0 return kadane of 2 array + sum*(k-2)
     * 3) if sum < 0 return kadane of 2 array
     */
    public static int kConcatenationMaxSum(int[] arr, int k) {
        int mod = 1000000007;
        // case 1 when k==1
        if (k == 1) {
            return kadanes(arr);
        }

        long sum = 0;
        for (int num : arr) {
            sum += num;
        }

        int[] kd = new int[2 * arr.length];
        for (int i = 0; i < kd.length; i++) {
            kd[i] = arr[i % arr.length];
        }

        long result = 0;
        if (sum <= 0) {
            //case 2
            result = kadanes(kd);
        } else {
            //case 3
            result = kadanes(kd) + (k - 2) * sum;
        }
        if (result < 0) {
            return 0;
        }
        return (int) (result % mod);
    }

    private static int kadanes(int[] arr) {
        int sum = 0, result = Integer.MIN_VALUE;
        for (int cur : arr) {
            sum += cur;
            if (result < sum) {
                result = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return result;
    }
}
