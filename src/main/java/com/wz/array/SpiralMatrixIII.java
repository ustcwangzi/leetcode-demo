package com.wz.array;

import java.util.Arrays;

/**
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
 * Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
 * Now, we walk in a clockwise spiral shape to visit every position in this grid.
 * Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
 * Eventually, we reach all R * C spaces of the grid.
 * Return a list of coordinates representing the positions of the grid in the order they were visited.
 *
 * Example 1:
 * Input: R = 1, C = 4, r0 = 0, c0 = 0
 * Output: [[0,0],[0,1],[0,2],[0,3]]
 *
 * Example 2:
 * @link ../../../../resource/SpiralMatrixIII.jpg
 * Input: R = 5, C = 6, r0 = 1, c0 = 4
 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],
 * [0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 */
public class SpiralMatrixIII {
    public static void main(String[] args) {
        int[][] result = spiralMatrixIII(1, 2, 0, 0);
        for (int[] array : result) {
            System.out.print(Arrays.toString(array));
        }

        System.out.println();

        result = spiralMatrixIII(5, 6, 1, 4);
        for (int[] array : result) {
            System.out.print(Arrays.toString(array));
        }
    }

    public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        //define all four possible directions to go to
        int[] dirs = new int[]{0, 1, 0, -1, 0};
        int len = 0;
        int d = 0;
        //total rows are equal to the number of elements and two columns for x and y coordinates
        int[][] matrix = new int[R * C][2];
        int k = 0;

        //filling in the first value in the matrix
        matrix[k++] = new int[]{r0, c0};
        //total elements in the matrix are R*C
        while (k < R * C) {
            // going east or west will increase the length of the spiral
            if (d == 0 || d == 2) {
                len++;
            }
            for (int i = 0; i < len; i++) {
                //change directions clockwise
                r0 += dirs[d];
                c0 += dirs[d + 1];
                //check to see if the coordinates are inside/outside the matrix
                if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
                    matrix[k++] = new int[]{r0, c0};
                }
            }
            d = (++d) % 4; // directions have values 0, 1, 2, 3
        }
        return matrix;
    }
}
