package com.wz.greedy;

/**
 * We have a two dimensional matrix A where each value is 0 or 1.
 * A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.
 * After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
 * Return the highest possible score.
 *
 * Example 1:
 * Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * Output: 39
 * Explanation:
 * Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *
 * Note:
 * 1. 1 <= A.length <= 20
 * 2. 1 <= A[0].length <= 20
 * 3. A[i][j] is 0 or 1.
 */
public class ScoreAfterFlippingMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 0, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0}
        };
        System.out.println(matrixScore(matrix));
    }

    /**
     * 每一行的最高位一定需要翻转为 1，共有 m 行，因此就是 (1 << (n - 1)) * m
     * 每一行的翻转情况就确定了，若还想增大数字之和，就只能看各列是否还能翻转了，而且是从次高位列开始看，因为最高位必须保证都是 1
     * 此时就要看它跟最高位是否相同了，若相同则会和最高位一样变为 1，否则会变为 0。翻转当前列是希望翻转之后 1 的个数要更多一些，
     * 所以就要统计每列当前 1 的个数，若小于 0 的个数，才进行翻转，然后乘以该列的值，对于第 j 列，其值为 1<<(n-1-j)
     */
    public static int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length, result = (1 << (n - 1)) * m;
        for (int j = 1; j < n; ++j) {
            // 统计 1 的数量
            int count = 0;
            for (int[] array : A) {
                count += (array[j] ^ array[0]);
            }
            result += Math.max(count, m - count) * (1 << (n - 1 - j));
        }
        return result;
    }
}
