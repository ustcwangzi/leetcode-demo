package com.wz.lists;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians),
 * return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
 * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j,
 * or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row,
 * that is, always ones may appear first and then zeros.
 *
 * Example 1:
 * Input: mat =
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 2
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 2
 * row 4 -> 5
 * Rows ordered from the weakest to the strongest are [2,0,3,1,4]
 *
 * Example 2:
 * Input: mat =
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 1
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 1
 * Rows ordered from the weakest to the strongest are [0,2,3,1]
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 */
public class TheKWeakestRowsInAMatrix {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        System.out.println(Arrays.toString(kWeakestRows(mat, 3)));

        mat = new int[][]{
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        System.out.println(Arrays.toString(kWeakestRows(mat, 2)));
    }

    /**
     * 方阵 mat 由若干军人和平民组成，分别用 0 和 1 表示，返回方阵中战斗力最弱的 k 行的索引，按从最弱到最强排序
     * tmp[i][0] 存储 i 行的索引，tmp[i][1] 存储 i 行中 1 的个数，然后根据 tmp[i][1] 排序，取出前 k 行
     */
    public static int[] kWeakestRows(int[][] mat, int k) {
        int[][] tmp = new int[mat.length][2];
        for (int i = 0; i < mat.length; i++) {
            tmp[i][0] = i;
            tmp[i][1] = (int) Arrays.stream(mat[i]).filter(value -> value == 1).count();
        }

        Arrays.parallelSort(tmp, Comparator.comparingInt(a -> a[1]));
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = tmp[i][0];
        }
        return result;
    }
}
