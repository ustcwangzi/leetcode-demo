package com.wz.greedy;

/**
 * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
 * A grid is said to be valid if all the cells above the main diagonal are zeros.
 * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
 *
 * Example 1:
 * @see ../../../../resource/MinimumSwapsToArrangeBinaryGrid1.jpg
 * Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
 * Output: 3
 *
 * Example 2:
 * @see ../../../../resource/MinimumSwapsToArrangeBinaryGrid2.jpg
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * Output: -1
 * Explanation: All rows are similar, swaps have no effect on the grid.
 *
 * Constraints:
 * 1. n == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= n <= 200
 * 4. grid[i][j] is 0 or 1
 */
public class MinimumSwapsToArrangeBinaryGrid {
    public static void main(String[] args) {
        System.out.println(minSwaps(new int[][]{
                {0, 0, 1},
                {1, 1, 0},
                {1, 0, 0}
        }));
    }

    /**
     * 每一行，需要 0 的个数是知道的，所以如果该不满足，就去找下面第一个遇见满足条件 row j,把 row  j 往上 swap
     */
    public static int minSwaps(int[][] grid) {
        int n = grid.length, result = 0;
        int[] count = countZero(grid);
        for (int i = 0; i < n; i++) {
            if (count[i] >= n - i - 1) {
                continue;
            }

            int j = i;
            // 找到满足第 i 行所需要 0 的个数的 row j, 然后把 j 往上移动
            while (j < n && count[j] < n - i - 1) {
                j++;
            }
            if (j == n) {
                return -1;
            }

            while (j > i) {
                swap(count, j--);
                result++;
            }
        }
        return result;
    }

    /**
     * 从右往左连续 0 的个数
     */
    private static int[] countZero(int[][] grid) {
        int n = grid.length;
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = n - 1; j >= 0 && grid[i][j] == 0; j--) {
                cur++;
            }
            count[i] = cur;
        }
        return count;
    }

    private static void swap(int[] array, int j) {
        int tmp = array[j];
        array[j] = array[j - 1];
        array[j - 1] = tmp;
    }
}
