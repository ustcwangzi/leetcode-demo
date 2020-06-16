package com.wz.lists;

import java.util.Arrays;

/**
 * Given a 2D integer matrix M representing the gray scale of an image,
 * you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down)
 * of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
 *
 * Note:
 * The value in the given matrix is in the range of [0, 255].
 * The length and width of the given matrix are in the range of [1, 150].
 *
 * Example 1:
 * Input:
 * [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 * Output:
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 * Explanation:
 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 */
public class ImageSmoother {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        int[][] result = imageSmoother(matrix);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 先对原数组进行逐个扫描，对于每一个位置，扫描其周围八个位置
     * 计算邻居个数cell、邻居累加和sum，然后计算结果
     */
    public static int[][] imageSmoother(int[][] M) {
        int m = M.length, n = M[0].length;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // cell和sum都需要包含当前位置
                int cell = 1, sum = M[i][j];
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k], y = j + dy[k];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        cell++;
                        sum += M[x][y];
                    }
                }
                result[i][j] = sum / cell;
            }
        }

        return result;
    }
}
