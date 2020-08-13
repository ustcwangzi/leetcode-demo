package com.wz.array;

/**
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square
 * with a sum less than or equal to threshold or return 0 if there is no such square.
 *
 * Example 1:
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 *
 * Example 2:
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * Output: 0
 *
 * Example 3:
 * Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * Output: 3
 *
 * Example 4:
 * Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * Output: 2
 */
public class MaximumSideLengthOfSquareWithSumLessThanOrEqualToThreshold {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 1, 3, 2, 4, 3, 2},
                {1, 1, 3, 2, 4, 3, 2},
                {1, 1, 3, 2, 4, 3, 2}
        };
        System.out.println(maxSideLength(mat, 4));

        mat = new int[][]{
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}
        };
        System.out.println(maxSideLength(mat, 1));
    }

    /**
     * 二维前缀和，preSum[i][j] 表示从 [0][0] 到 [i-1][j-1] 的元素矩形之和
     * 枚举矩形的边长，然后枚举每个矩形，如果求出的矩形元素之和小于等于 threshold，则返回边长
     */
    public static int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int result = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 枚举每个边长的矩形
                for (int k = 1; k <= Math.min(i, j); k++) {
                    int sum = preSum[i][j] - preSum[i - k][j] - preSum[i][j - k] + preSum[i - k][j - k];
                    if (sum <= threshold) {
                        result = Math.max(result, k);
                    }
                }
            }
        }

        return result;
    }
}
