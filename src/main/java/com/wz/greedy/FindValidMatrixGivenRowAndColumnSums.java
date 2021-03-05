package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the sum of the elements in the ith row and colSum[j] is the sum of the elements of the jth column of a 2D matrix. In other words, you do not know the elements of the matrix, but you do know the sums of each row and column.
 *
 * Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies the rowSum and colSum requirements.
 *
 * Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed that at least one matrix that fulfills the requirements exists.
 *
 *
 *
 * Example 1:
 *
 * Input: rowSum = [3,8], colSum = [4,7]
 * Output: [[3,0],
 *          [1,7]]
 * Explanation:
 * 0th row: 3 + 0 = 3 == rowSum[0]
 * 1st row: 1 + 7 = 8 == rowSum[1]
 * 0th column: 3 + 1 = 4 == colSum[0]
 * 1st column: 0 + 7 = 7 == colSum[1]
 * The row and column sums match, and all matrix elements are non-negative.
 * Another possible matrix is: [[1,2],
 *                              [3,5]]
 *
 * Constraints:
 * 1. 1 <= rowSum.length, colSum.length <= 500
 * 2. 0 <= rowSum[i], colSum[i] <= 10^8
 * 3. sum(rows) == sum(columns)
 */
public class FindValidMatrixGivenRowAndColumnSums {
    public static void main(String[] args) {
        int[][] result = restoreMatrix(new int[]{3, 8}, new int[]{4, 7});
        Arrays.stream(result).forEach(array -> System.out.println(Arrays.toString(array)));
    }

    /**
     * 对于每一个位置 result[i][j]，一开始写入 Math.min(rowSum[i], colSum[j])，
     * 这样操作之后，会使得 rowSum[i], colSum[j] 之中有一个变成 0
     * 如此操作直到最后一个位置上的时候，理应让 sum(rows) 和 sum(columns) 都变成 0
     * 因此每次操作之后都需要在 rowSum[i] 和 colSum[j] 里减去这个较小值
     */
    public static int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= result[i][j];
                colSum[j] -= result[i][j];
            }
        }
        return result;
    }
}
