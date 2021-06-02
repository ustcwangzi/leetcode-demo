package com.wz.hash;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an m x n binary matrix matrix.
 *
 * You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).
 *
 * Return the maximum number of rows that have all values equal after some number of flips.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[0,1],[1,1]]
 * Output: 1
 * Explanation: After flipping no values, 1 row has all values equal.
 * Example 2:
 *
 * Input: matrix = [[0,1],[1,0]]
 * Output: 2
 * Explanation: After flipping values in the first column, both rows have equal values.
 * Example 3:
 *
 * Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
 * Output: 2
 * Explanation: After flipping values in the first two columns, the last two rows have equal values.
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is either 0 or 1.
 */
public class FlipColumnsForMaximumNumberOfEqualRows {
    public static void main(String[] args) {
        System.out.println(maxEqualRowsAfterFlips(new int[][]{
                {0, 0, 0},
                {0, 0, 1},
                {1, 1, 0}
        }));
    }

    /**
     * 站在结束时候的状态来进行反推
     * 如果最终状态下 i-row 和 j-row 完全相同，那么其最初状态一定也是一样的
     * 如果最终状态下 i-row 和 j-row 完全相反，那么其最初状态一定也是相反的
     * 因此问题就转化为了求 matrix 中相同或相反的最多行数
     * 可以对每一行进行编码来快速求解，因为相同或相反都要统计，因此编码时只需要和每一行的首字符进行比较来决定当前字符的编码
     * 如果和首字符相同就编码为 'a'，否则就编码为 'b'，将每一行编码结果存入 map 中计算频次，最后结果就是频次最大的那个
     */
    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        int n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>();
        for (int[] array : matrix) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                builder.append(array[j] == array[0] ? 'a' : 'b');
            }
            map.put(builder.toString(), map.getOrDefault(builder.toString(), 0) + 1);
        }
        return Collections.max(map.values());
    }
}
