package com.wz.lists;

/**
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 * Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
 * A[i] == B[j];
 * The line we draw does not intersect any other connecting (non-horizontal) line.
 * Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.
 * Return the maximum number of connecting lines we can draw in this way.
 *
 * Example 1:
 * Input: A = [1,4,2], B = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
 *
 * Example 2:
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 *
 * Example 3:
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 */
public class UncrossedLines {
    public static void main(String[] args) {
        int[] A = new int[]{1, 4, 2}, B = new int[]{1, 2, 4};
        System.out.println(maxUncrossedLines(A, B));

        A = new int[]{2, 5, 1, 2, 5};
        B = new int[]{10, 5, 2, 1, 5, 2};
        System.out.println(maxUncrossedLines(A, B));

        A = new int[]{1, 3, 7, 1, 7, 5};
        B = new int[]{1, 9, 2, 5, 1};
        System.out.println(maxUncrossedLines(A, B));
    }

    /**
     * 动态规划
     * dp[i][j]：代表截止到A[i]与B[j]，最多可以的连线条数
     * 动态转移方程：
     * A[i]==B[j] 时，dp[i][j] = dp[i-1][j-1] + 1
     * A[i]!=B[j] 时，dp[i][j] = max(dp[i][j-1], dp[i-1][j])
     * 初始状态：
     * A[0]==B[j] 时，dp[0][j]=0+1
     * A[i]!=B[j] 时，dp[0][j]=0
     * A[i]==B[0] 时，dp[i][0]=0+1
     * A[i]!=B[j] 时，dp[i][0]=0
     * 为方便处理，可以往前多加一位全为0，dp[0][j]或者dp[i][0]都为0
     */
    public static int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
