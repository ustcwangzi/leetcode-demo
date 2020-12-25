package com.wz.dynamicprogramming;

/**
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 *
 * Example 1:
 * Input: mat = [[1,0,1],
 * [1,1,0],
 * [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 *
 * Example 2:
 * Input: mat = [[0,1,1,0],
 * [0,1,1,1],
 * [1,1,1,0]]
 * Output: 24
 * Explanation:
 * There are 8 rectangles of side 1x1.
 * There are 5 rectangles of side 1x2.
 * There are 2 rectangles of side 1x3.
 * There are 4 rectangles of side 2x1.
 * There are 2 rectangles of side 2x2.
 * There are 2 rectangles of side 3x1.
 * There is 1 rectangle of side 3x2.
 * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
 *
 * Constraints:
 * 1. 1 <= rows <= 150
 * 2. 1 <= columns <= 150
 * 3. 0 <= mat[i][j] <= 1
 */
public class CountSubmatricesWithAllOnes {
    public static void main(String[] args) {
        System.out.println(countSquares(new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        }));
    }

    /**
     * 循环每个节点，计算将此节点作为右下角的所有矩形
     * height[j] 表示每一行第 j 列的高度，向左遍历，找到可以延伸的高度 min，将 min 累加到结果中
     */
    public static int countSquares(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] height = new int[n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
                for (int k = j, min = height[j]; k >= 0 && min > 0; k--) {
                    min = Math.min(min, height[k]);
                    result += min;
                }
            }
        }
        return result;
    }
}
