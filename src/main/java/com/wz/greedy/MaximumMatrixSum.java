package com.wz.greedy;

/**
 * You are given an n x n integer matrix. You can do the following operation any number of times:
 * 1. Choose any two adjacent elements of matrix and multiply each of them by -1.
 * 2. Two elements are considered adjacent if and only if they share a border.
 * Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.
 *
 * Example 1:
 * @link ../../../../resource/MaximumMatrixSum1.jpg
 * Input: matrix = [[1,-1],[-1,1]]
 * Output: 4
 * Explanation: We can follow the following steps to reach sum equals 4:
 * - Multiply the 2 elements in the first row by -1.
 * - Multiply the 2 elements in the first column by -1.
 *
 * Example 2:
 * @link ../../../../resource/MaximumMatrixSum2.jpg
 * Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * Output: 16
 * Explanation: We can follow the following step to reach sum equals 16:
 * - Multiply the 2 last elements in the second row by -1.
 *
 * Constraints:
 * 1. n == matrix.length == matrix[i].length
 * 2. 2 <= n <= 250
 * 3. -10^5 <= matrix[i][j] <= 10^5
 */
public class MaximumMatrixSum {
    public static void main(String[] args) {
        System.out.println(maxMatrixSum(new int[][]{{1, -1}, {-1, 1}}));
        System.out.println(maxMatrixSum(new int[][]{{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}}));
    }

    /**
     * 通过一次或多次操作，可同时完成任意 2 个相邻或不相邻元素的符号转化，因此结合贪心算法求解：
     * 若负元素个数为偶数，可以将所有元素转化为非负整数
     * 若负元素个数为奇数，则将绝对值最小的元素保留为负
     */
    public static long maxMatrixSum(int[][] matrix) {
        // 全部元素的正数之和
        long result = 0;
        int n = matrix.length, min = Integer.MAX_VALUE, negativeCount = 0;
        for (int[] array : matrix) {
            for (int i = 0; i < n; i++) {
                negativeCount += array[i] < 0 ? 1 : 0;
                result += Math.abs(array[i]);
                // 最小的元素
                min = Math.min(min, Math.abs(array[i]));
            }
        }
        // 奇数个负数，最小的元素不转换
        if ((negativeCount & 1) == 1) {
            result -= 2L * min;
        }
        return result;
    }
}
