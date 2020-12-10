package com.wz.dynamicprogramming;

/**
 * Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
 * Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product
 * of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
 *
 * Example 2:
 * Input: [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
 *
 * Note:
 * 1. 3 <= A.length <= 50
 * 2. 1 <= A[i] <= 100
 */
public class MinimumScoreTriangulationOfPolygon {
    public static void main(String[] args) {
        System.out.println(minScoreTriangulation(new int[]{3, 7, 4, 5}));
    }

    /**
     * dp[i][j] 表示定点为数组 A[i,j+1] 构成的多边形的最小结果，最终要求的是 dp[0][n-1]
     * dp[i][j] = min{dp[i][j], dp[i][mid] + cur_triangle + dp[mid][j]}
     * 因为要用到 dp[mid][j] 的结果，所以 j 从小到大，i 从大到小，j 的最小值为2，然后遍历 i～j 中的每个 k
     */
    public static int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                for (int k = i + 1; k < j; k++) {
                    if (dp[i][j] == 0) {
                        dp[i][j] = dp[i][k] + dp[k][j] + A[i] * A[j] * A[k];
                    } else {
                        dp[i][j] = Math.min(dp[i][k] + dp[k][j] + A[i] * A[j] * A[k], dp[i][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
