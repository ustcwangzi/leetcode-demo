package com.wz.dynamicprogramming;

/**
 * Given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left.
 * Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 * Find the maximum points you can get.
 *
 * Example 1:
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 *
 * Constraints:
 * 1. 1 <= boxes.length <= 100
 * 2. 1 <= boxes[i] <= 100
 */
public class RemoveBoxes {
    public static void main(String[] args) {
        System.out.println(removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1}));
    }

    /**
     * dp[i][j][k] 表示当 boxes[i] 左边有 k 个数字跟其相等时，[i,j] 内能获得的最大积分，那么目标就是求 dp[0][n-1][0]，
     * 对于 dp[i][j][k]，如果移除 boxes[i]，得到 (1+k)*(1+k) + dp[i+1][j][0]
     * 当某个位置 m，有 boxes[i]==boxes[m] 时，应该考虑先移除 [i+1,m-1] 这部分，得到积分 dp[i+1][m-1][0]，然后再处理剩下的部分，
     * 得到积分 dp[m][j][k+1]，这里 k+1 原因是，移除了中间的部分后，原本和 boxes[m] 不相邻的 boxes[i] 现在相邻了，
     * 又因为二者值相同，所以 k 应该加 1，因为 k 的定义就是左边相等的数字的个数
     */
    public static int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return dfs(boxes, 0, boxes.length - 1, 0, dp);
    }

    private static int dfs(int[] boxes, int i, int j, int k, int[][][] dp) {
        if (j < i) {
            return 0;
        }
        if (dp[i][j][k] > 0) {
            return dp[i][j][k];
        }
        int result = (1 + k) * (1 + k) + dfs(boxes, i + 1, j, 0, dp);
        for (int m = i + 1; m <= j; ++m) {
            if (boxes[m] == boxes[i]) {
                result = Math.max(result, dfs(boxes, i + 1, m - 1, 0, dp) + dfs(boxes, m, j, k + 1, dp));
            }
        }
        dp[i][j][k] = result;
        return result;
    }
}
