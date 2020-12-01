package com.wz.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0.
 * What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.
 * An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going
 * up, down, left, and right, and made of 1s. This is demonstrated in the diagrams below.
 * Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.
 * Examples of Axis-Aligned Plus Signs of Order k:
 * Order 1:
 * 000
 * 010
 * 000
 *
 * Order 2:
 * 00000
 * 00100
 * 01110
 * 00100
 * 00000
 *
 * Order 3:
 * 0000000
 * 0001000
 * 0001000
 * 0111110
 * 0001000
 * 0001000
 * 0000000
 *
 * Example 1:
 * Input: N = 5, mines = [[4, 2]]
 * Output: 2
 * Explanation:
 * 11111
 * 11111
 * 11111
 * 11111
 * 11011
 * In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.
 *
 * Note:
 * 1. N will be an integer in the range [1, 500].
 * 2. mines will have length at most 5000.
 * 3. mines[i] will be length 2 and consist of integers in the range [0, N-1].
 * 4. (Additionally, programs submitted in C, C++, or C# will be judged with a slightly smaller time limit.)
 */
public class LargestPlusSign {
    public static void main(String[] args) {
        System.out.println(orderOfLargestPlusSign(5, new int[][]{{4, 2}}));
    }

    /**
     * 建立四个方向的dp数组，dp[i][j] 表示 (i, j) 位置上该特定方向连续1的个数，那么就需要4个二维dp数组，比如：
     * 原数组：
     * 1  0  1  0
     * 1  1  1  1
     * 1  0  1  1
     * 那么建立left数组是当前及其左边连续1的个数，如下所示：
     * 1  0  1  0
     * 1  2  3  4
     * 1  0  1  2
     * right数组是当前及其右边连续1的个数，如下所示：
     * 1  0  1  0
     * 4  3  2  1
     * 1  0  2  1
     * up数组是当前及其上边连续1的个数，如下所示：
     * 1  0  1  0
     * 2  1  2  1
     * 3  0  3  2
     * down数组是当前及其下边连续1的个数，如下所示：
     * 3  0  3  0
     * 2  1  2  2
     * 1  0  1  1
     * 需要做的是在这四个dp数组中的相同位置的四个值中取最小的一个，然后在所有的这些去除的最小值中选最大一个返回即可。
     * 为了节省空间，不用四个二维dp数组，而只用一个就可以了，因为对于每一个特定位置，只需要保留较小值，所以在更新的时候，只需要跟原来值相比取较小值即可。
     * 在计算down数组的时候，就可以直接更新结果了，因为四个值都已经计算过了，就不用再重新在外面开for循环了
     */
    public static int orderOfLargestPlusSign(int N, int[][] mines) {
        int result = 0, count = 0;
        int[][] dp = new int[N][N];
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines) {
            set.add(mine[0] * N + mine[1]);
        }

        for (int j = 0; j < N; j++) {
            count = 0;
            // up
            for (int i = 0; i < N; i++) {
                count = set.contains(i * N + j) ? 0 : count + 1;
                dp[i][j] = count;
            }

            count = 0;
            // down
            for (int i = N - 1; i >= 0; i--) {
                count = set.contains(i * N + j) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }

        for (int i = 0; i < N; ++i) {
            count = 0;
            // left
            for (int j = 0; j < N; ++j) {
                count = set.contains(i * N + j) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
            }

            count = 0;
            // right
            for (int j = N - 1; j >= 0; --j) {
                count = set.contains(i * N + j) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
}
