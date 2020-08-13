package com.wz.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a m * n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
 * A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
 *
 * Example 1:
 * Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * Output: [15]
 * Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column
 *
 * Example 2:
 * Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * Output: [12]
 * Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
 *
 * Example 3:
 * Input: matrix = [[7,8],[1,2]]
 * Output: [7]
 */
public class LuckyNumbersInMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 7, 8}, {9, 11, 13}, {15, 16, 17}
        };
        System.out.println(luckyNumbers(matrix));
    }

    /**
     * lucky number: 既是每行最小的数，也是每列最大的数
     * 设置两个数组存放每行的最小值、每列的最大值
     * 燃尽进行比较比较，两者相等时加入结果中
     */
    public static List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rowMin = new int[m], colMax = new int[n];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }

        List<Integer> result = new ArrayList<>(Math.max(m, n));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowMin[i] == colMax[j]) {
                    result.add(rowMin[i]);
                }
            }
        }

        return result;
    }
}
