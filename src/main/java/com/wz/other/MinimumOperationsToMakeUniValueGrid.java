package com.wz.other;

import java.util.Arrays;

/**
 * You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.
 * A uni-value grid is a grid where all the elements of it are equal.
 * Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.
 *
 * Example 1:
 * @link ../../../../resource/MinimumOperationsToMakeUniValueGrid1.jpg
 * Input: grid = [[2,4],[6,8]], x = 2
 * Output: 4
 * Explanation: We can make every element equal to 4 by doing the following:
 * - Add x to 2 once.
 * - Subtract x from 6 once.
 * - Subtract x from 8 twice.
 * A total of 4 operations were used.
 *
 * Example 2:
 * @link ../../../../resource/MinimumOperationsToMakeUniValueGrid2.jpg
 * Input: grid = [[1,5],[2,3]], x = 1
 * Output: 5
 * Explanation: We can make every element equal to 3.
 *
 * Example 3:
 * @link ../../../../resource/MinimumOperationsToMakeUniValueGrid3.jpg
 * Input: grid = [[1,2],[3,4]], x = 2
 * Output: -1
 * Explanation: It is impossible to make every element equal.
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 10^5
 * 4. 1 <= m * n <= 10^5
 * 5. 1 <= x, grid[i][j] <= 10^4
 */
public class MinimumOperationsToMakeUniValueGrid {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[][]{{2, 4}, {6, 8}}, 2));
        System.out.println(minOperations(new int[][]{{1, 2}, {3, 4}}, 2));
    }

    /**
     * 将每个数变更为中位数时操作总操作次数最少，因此先将二维数字打平为一维数组 array，然后对 array 进行排序，得到中位数 median
     * 接着遍历 array，若当前元素 num==median，则不用变更，否则判断 num-median 能否被 x 整除，若不能整除则无法操作直接返回 -1
     * 若能整除，则将操作次数为 (num-median)/x，将该操作次数累加到结果中
     */
    public static int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int[] array = new int[m * n];
        int index = 0;
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                array[index++] = row[j];
            }
        }

        Arrays.parallelSort(array);
        int median = array[(array.length - 1) / 2], result = 0;
        for (int num : array) {
            if (num == median) {
                continue;
            }
            if (Math.abs(num - median) % x != 0) {
                return -1;
            }
            result += Math.abs(num - median) / x;
        }
        return result;
    }
}
