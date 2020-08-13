package com.wz.array;

/**
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 * Return the length of a maximum size turbulent subarray of A.
 *
 * Example 1:
 * Input: [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
 *
 * Example 2:
 * Input: [4,8,12,16]
 * Output: 2
 *
 * Example 3:
 * Input: [100]
 * Output: 1
 */
public class LongestTurbulentSubarray {
    public static void main(String[] args) {
        int[] A = new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9};
        System.out.println(maxTurbulenceSize(A));

        A = new int[]{4, 8, 12, 16};
        System.out.println(maxTurbulenceSize(A));
    }

    /**
     * 求最长的连续波动子数组，在这个子数组中，相邻数值交替大小
     * 动态规划，dp[i] 表示以 i 结尾的的连续波动子数组长度
     * 转移方程式也比较容易观察出来：
     * 对于位置i，如果满足 (A[i-1] > A[i-2] && A[i-1] > A[i]) || (A[i-1] < A[i-2] && A[i-1] < A[i])，则 dp[i] = dp[i-1] + 1；
     * 不满足条件时，如果 A[i] == A[i-1]，说明当前数字与前一个数字相同，dp[i] = 1；否则当前数字与前一个数字不相同，dp[i] = 2。
     */
    public static int maxTurbulenceSize(int[] A) {
        if (A.length == 1) {
            return 1;
        }

        int[] dp = new int[A.length];
        dp[0] = 1;
        dp[1] = A[0] == A[1] ? 1 : 2;
        int result = Math.max(dp[0], dp[1]);
        for (int i = 2; i < A.length; i++) {
            if ((A[i - 2] > A[i - 1] && A[i - 1] < A[i]) || (A[i - 2] < A[i - 1] && A[i - 1] > A[i])) {
                dp[i] = dp[i - 1] + 1;
            } else if (A[i] == A[i - 1]) {
                dp[i] = 1;
            } else {
                dp[i] = 2;
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
