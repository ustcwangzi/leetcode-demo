package com.wz.dynamicprogramming;

/**
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
 * and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 *
 * Example 1:
 * Input: A = [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 *
 * Example 2:
 * Input: A = [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 *
 * Constraints:
 * 1. 2 <= A.length <= 1000
 * 2. 0 <= A[i] <= 500
 */
public class LongestArithmeticSubsequence {
    public static void main(String[] args) {
        System.out.println(longestArithSeqLength(new int[]{3, 6, 9, 12}));
    }

    /**
     * dp[i][j] 表示以 nums[i] 结尾，差值为 j 的满足题意的最长子数组长度。因为差值有可能是正数也有可能是负数，
     * 这里做一个处理，注意题设给的数据范围是从0到500，所以最极端的等差有可能是从-500到500，所以数组的第二维的长度给1001，
     * 对于每一个 nums[i] ，去找 0～i 范围内每两个数字之间的等差，对这个值无条件 + 500，这样一些小于0的差值也就被存到了数组里面。
     * 更新 dp[i][diff]，同时更新 max。最后返回 max + 1，因为需要加上自身
     */
    public static int longestArithSeqLength(int[] A) {
        int max = 0;
        int[][] dp = new int[A.length + 1][1001];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j] + 500;
                dp[i][diff] = dp[j][diff] + 1;
                max = Math.max(max, dp[i][diff]);
            }
        }
        return max + 1;
    }
}
