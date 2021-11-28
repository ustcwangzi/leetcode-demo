package com.wz.dynamicprogramming;

/**
 * You are given an m x n integer matrix mat and an integer target.
 * Choose one integer from each row in the matrix such that the absolute difference between target and the sum of the chosen elements is minimized.
 * Return the minimum absolute difference.
 * The absolute difference between two numbers a and b is the absolute value of a - b.
 *
 * Example 1:
 * @link ../../../../resource/MinimizeTheDifferenceBetweenTargetAndChosenElements1.jpg
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * Output: 0
 * Explanation: One possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 5 from the second row.
 * - Choose 7 from the third row.
 * The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.
 *
 * Example 2:
 * @link ../../../../resource/MinimizeTheDifferenceBetweenTargetAndChosenElements2.jpg
 * Input: mat = [[1],[2],[3]], target = 100
 * Output: 94
 * Explanation: The best possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 2 from the second row.
 * - Choose 3 from the third row.
 * The sum of the chosen elements is 6, and the absolute difference is 94.
 *
 * Example 3:
 * @link ../../../../resource/MinimizeTheDifferenceBetweenTargetAndChosenElements3.jpg
 * Input: mat = [[1,2,9,8,7]], target = 6
 * Output: 1
 * Explanation: The best choice is to choose 7 from the first row.
 * The absolute difference is 1.
 *
 * Constraints:
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n <= 70
 * 4. 1 <= mat[i][j] <= 70
 * 5. 1 <= target <= 800
 */
public class MinimizeTheDifferenceBetweenTargetAndChosenElements {
    public static void main(String[] args) {
        System.out.println(minimizeTheDifference(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 13));
        System.out.println(minimizeTheDifference(new int[][]{{1}, {2}, {3}}, 100));
    }

    /**
     * DP + DFS
     * dp[i][j] 表示当前行 i 和之前行每行选一个数可能构成的和，由于所有数的最大值为 70，而行数最大也为 70，故最大和为 4900
     */
    public static int minimizeTheDifference(int[][] mat, int target) {
        return dfs(mat, 0, target, 0, new Integer[mat.length][5000]);
    }

    private static int dfs(int[][] mat, int index, int target, int sum, Integer[][] dp) {
        if (index == mat.length) {
            return Math.abs(sum - target);
        }
        if (dp[index][sum] != null) {
            return dp[index][sum];
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < mat[0].length; i++) {
            result = Math.min(result, dfs(mat, index + 1, target, sum + mat[index][i], dp));
        }
        return dp[index][sum] = result;
    }
}
