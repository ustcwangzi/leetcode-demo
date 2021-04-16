package com.wz.array;

import java.util.PriorityQueue;

/**
 * You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.
 * The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).
 * Find the kth largest value (1-indexed) of all the coordinates of matrix.
 *
 * Example 1:
 * Input: matrix = [[5,2],[1,6]], k = 1
 * Output: 7
 * Explanation: The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
 *
 * Example 2:
 * Input: matrix = [[5,2],[1,6]], k = 2
 * Output: 5
 * Explanation: The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.
 *
 * Constraints:
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= m, n <= 1000
 * 4. 0 <= matrix[i][j] <= 10^6
 * 5. 1 <= k <= m * n
 */
public class FindKthLargestXORCoordinateValue {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5, 2}, {1, 6}};
        System.out.println(kthLargestValue(matrix, 1));
        System.out.println(kthLargestValue(matrix, 2));
    }

    /**
     * arr[i][j] = arr[i][j-1] xor arr[i-1][j] xor arr[i-1][j-1] xor matrix[i-1][j-1]
     * 依次计算每个位置的 XOR 结果，使用小根堆记录结果，最后返回堆顶元素
     */
    public static int kthLargestValue(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        int m = matrix.length, n = matrix[0].length;
        int[][] xorMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    xorMatrix[i][j] = j == 0 ? matrix[i][j] : matrix[i][j] ^ xorMatrix[i][j - 1];
                } else if (j == 0) {
                    xorMatrix[i][j] = matrix[i][j] ^ xorMatrix[i - 1][j];
                } else {
                    xorMatrix[i][j] = matrix[i][j] ^ xorMatrix[i - 1][j] ^ xorMatrix[i][j - 1] ^ xorMatrix[i - 1][j - 1];
                }

                queue.add(xorMatrix[i][j]);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }
        return queue.peek();
    }
}
