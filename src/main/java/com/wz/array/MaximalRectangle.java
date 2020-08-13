package com.wz.array;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example:
 * Input:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalRectangle(matrix));
    }

    /**
     * 思路与{@link LargestRectangleInHistogram}类似
     * 将每一列连续的1看作是柱子，因为会存在0，那么对于每一行可以单独求最大面积，更新结果
     * 第一行：1 0 1 0 0
     * 第二行：2 0 2 1 1
     * 第三行：3 1 3 2 2
     * 第四行：4 0 0 3 0
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int result = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            // 求出每一列的高度
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            // 更新面积
            result = Math.max(result, maxRectangle(heights));
        }
        return result;
    }

    /**
     * 根据高度求面积
     */
    private static int maxRectangle(int[] heights) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                result = Math.max(result, heights[stack.pop()] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            result = Math.max(result, heights[stack.pop()] * (stack.isEmpty() ?
                    heights.length : heights.length - stack.peek() - 1));
        }
        return result;
    }
}
