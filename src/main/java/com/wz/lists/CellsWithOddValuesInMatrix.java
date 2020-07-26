package com.wz.lists;

/**
 * Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where
 * indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
 * Return the number of cells with odd values in the matrix after applying the increment to all indices.
 *
 * Example 1:
 * Input: n = 2, m = 3, indices = [[0,1],[1,1]]
 * Output: 6
 * Explanation: Initial matrix = [[0,0,0],[0,0,0]].
 * After applying first increment it becomes [[1,2,1],[0,1,0]].
 * The final matrix will be [[1,3,1],[1,3,1]] which contains 6 odd numbers.
 *
 * Example 2:
 * Input: n = 2, m = 2, indices = [[1,1],[0,0]]
 * Output: 0
 * Explanation: Final matrix = [[2,2],[2,2]]. There is no odd number in the final matrix.
 */
public class CellsWithOddValuesInMatrix {
    public static void main(String[] args) {
        int[][] indices = new int[][]{{0, 1}, {1, 1}};
        System.out.println(oddCells(2, 3, indices));

        indices = new int[][]{{1, 1}, {0, 0}};
        System.out.println(oddCells(2, 2, indices));
    }

    /**
     * 遍历 indices，对指定的行、列对应位置加1，然后统计奇数个数即可
     */
    public static int oddCells(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        for (int[] array : indices) {
            // 对所指定的行进行加1
            for (int j = 0; j < m; j++) {
                matrix[array[0]][j] += 1;
            }
            // 对所指定的列进行加1
            for (int i = 0; i < n; i++) {
                matrix[i][array[1]] += 1;
            }
        }

        int result = 0;
        for (int[] array : matrix) {
            for (int value : array) {
                if (value % 2 != 0) {
                    result++;
                }
            }
        }
        return result;
    }
}
