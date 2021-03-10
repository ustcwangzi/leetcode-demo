package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
 * Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.
 *
 * Example 1:
 * @see ../../../../resource/LargestSubmatrixWithRearrangements1.jpg
 * Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * Output: 4
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 4.
 *
 * Example 2:
 * @see ../../../../resource/LargestSubmatrixWithRearrangements2.jpg
 * Input: matrix = [[1,0,1,0,1]]
 * Output: 3
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 3.
 *
 * Constraints:
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= m * n <= 10^5
 * 4. matrix[i][j] is 0 or 1.
 */
public class LargestSubmatrixWithRearrangements {
    public static void main(String[] args) {
        System.out.println(largestSubmatrix(new int[][]{
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 1}
        }));
    }

    /**
     * 遍历数组，计算竖直方向以该点为底的 1 的最大高度，也就是说，计算每个点上面有多少个连续的 1，
     * 然后遍历每行，计算以该行为底的最大矩形面积，就很简单了：
     * 已经知道了每个点上方有多少个连续的 1，在遍历每行时，可以直接获取到以该行为底的所有列的高度(连续 1 的高度)
     * 对以改行为底的所有连续列排序，从高到低依次计算矩形的面积即可
     * 以例1为例说明该过程： @see ../../../../resource/LargestSubmatrixWithRearrangements.jpg
     */
    public static int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            Arrays.parallelSort(matrix[i]);
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] > 0) {
                    result = Math.max(result, matrix[i][j] * (n - j));
                }
            }
        }
        return result;
    }
}
