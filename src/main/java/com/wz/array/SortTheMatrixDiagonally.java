package com.wz.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a m * n matrix mat of integers, sort it diagonally in ascending order from the top-left to the bottom-right then return the sorted array.
 * @link ../../../../resource/SortTheMatrixDiagonally.jpg
 *
 * Example 1:
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class SortTheMatrixDiagonally {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}
        };
        int[][] result = diagonalSort(mat);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 分别从第一列、第一行开始遍历数组，遍历每一个对角线时，对该对角线的元素进行排序，然后再放入结果数组中
     */
    public static int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] result = new int[m][n];
        // 从第一列开始进行对角线遍历
        for (int i = 0; i < m; i++) {
            int x = i, y = 0;
            List<Integer> list = new LinkedList<>();
            while (x < m && y < n) {
                list.add(mat[x++][y++]);
            }
            Collections.sort(list);

            x = i;
            y = 0;
            int index = 0;
            // 将排序后的元素放入结果中
            while (x < m && y < n) {
                result[x++][y++] = list.get(index++);
            }
        }

        // 从第一行开始进行对角线遍历
        for (int j = 1; j < n; j++) {
            int x = 0, y = j;
            List<Integer> list = new LinkedList<>();
            while (x < m && y < n) {
                list.add(mat[x++][y++]);
            }
            Collections.sort(list);

            x = 0;
            y = j;
            int index = 0;
            // 将排序后的元素放入结果中
            while (x < m && y < n) {
                result[x++][y++] = list.get(index++);
            }
        }

        return result;
    }
}
