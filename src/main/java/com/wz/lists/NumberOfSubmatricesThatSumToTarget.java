package com.wz.lists;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate
 * that is different: for example, if x1 != x1'.
 *
 * Example 1:
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 *
 * Example 2:
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 */
public class NumberOfSubmatricesThatSumToTarget {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        System.out.println(numSubmatrixSumTarget(matrix, 0));

        matrix = new int[][]{
                {1, -1},
                {-1, 1},
        };
        System.out.println(numSubmatrixSumTarget(matrix, 0));
    }

    /**
     * 思路与{@link SubarraySumEqualsK} 类似，在其基础上加上了对列的枚举
     * 先计算每一行的前缀和，然后在每一列上进行遍历，这时从上往下一行一行的看，又变成了 sum(subarray)==target 的问题
     */
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 计算每一行的前缀和
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        int result = 0;
        // 从l向r进行列的遍历
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                // 记录前缀和出现的次数
                Map<Integer, Integer> map = new HashMap<>();
                // 处理 preSum == target 的情况
                map.put(0, 1);
                int preSum = 0;
                // 第i行中第l列到第r列的前缀和
                for (int i = 0; i < m; i++) {
                    preSum += matrix[i][r] - (l == 0 ? 0 : matrix[i][l - 1]);
                    result += map.getOrDefault(preSum - target, 0);
                    map.put(preSum, map.getOrDefault(preSum, 0) + 1);
                }
            }
        }

        return result;
    }
}
