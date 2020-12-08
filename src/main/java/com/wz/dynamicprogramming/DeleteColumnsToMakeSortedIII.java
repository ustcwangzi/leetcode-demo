package com.wz.dynamicprogramming;

/**
 * We are given an array A of N lowercase letter strings, all of the same length.
 * Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.
 * For example, if we have an array A = ["babca","bbazb"] and deletion indices {0, 1, 4}, then the final array after deletions is ["bc","az"].
 * Suppose we chose a set of deletion indices D such that after deletions, the final array has every element (row) in lexicographic order.
 * For clarity, A[0] is in lexicographic order (ie. A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]), A[1]
 * is in lexicographic order (ie. A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]), and so on.
 * Return the minimum possible value of D.length.
 *
 * Example 1:
 * Input: ["babca","bbazb"]
 * Output: 3
 * Explanation: After deleting columns 0, 1, and 4, the final array is A = ["bc", "az"].
 * Both these rows are individually in lexicographic order (ie. A[0][0] <= A[0][1] and A[1][0] <= A[1][1]).
 * Note that A[0] > A[1] - the array A isn't necessarily in lexicographic order.
 *
 * Example 2:
 * Input: ["ghi","def","abc"]
 * Output: 0
 * Explanation: All rows are already lexicographically sorted.
 *
 * Note:
 * 1. 1 <= A.length <= 100
 * 2. 1 <= A[i].length <= 100
 */
public class DeleteColumnsToMakeSortedIII {
    public static void main(String[] args) {
        System.out.println(minDeletionSize(new String[]{"babca", "bbazb"}));
    }

    /**
     * 与 {@link LongestIncreasingSubsequence} 类似
     * 最差的情况就是每行都是倒序的，就要移除 n-1 列，只留下一列，最好的情况就是给定每行已经都是有序的，则一列都不用移除
     * 假如只有一个字符串，为了使其有序，需要移除最小的列数，移除之后剩下的就是有序的。那么其实就是等同于找出该字符串中的最大递增序列的长度，
     * 然后用总长度减去这个 LIS 长度，即为最少移除的列数。若有多行的情况，这个 LIS 必须是所有行都满足的，才符合题意。
     * dp[i] 表示以 A[][i] 为结尾的最长递增子串的长度，对于每一个 A[][i]，从该行第一个数再搜索到 i，此时由于有多行，每一行都要判断一下，
     * 假如出现 A[][j] 大于 A[][i] 的情况，说明当前列不能组成 LIS，直接 break。只有每行都符合要求，更新 dp[i] = max{dp[i], dp[j]+1}。
     * 当每次 dp[i] 更新了之后，用 n-dp[i] 来更新结果即可
     */
    public static int minDeletionSize(String[] A) {
        int m = A.length, n = A[0].length(), result = n - 1, k = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                for (k = 0; k < m; k++) {
                    if (A[k].charAt(j) > A[k].charAt(i)) {
                        break;
                    }
                }
                if (k == m) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.min(result, n - dp[i]);
        }
        return result;
    }
}
