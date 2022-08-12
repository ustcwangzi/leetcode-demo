package com.wz.dfs;

/**
 * You are given four integers, m, n, introvertsCount, and extrovertsCount. You have an m x n grid, and there are two types of people:
 * introverts and extroverts. There are introvertsCount introverts and extrovertsCount extroverts.
 * You should decide how many people you want to live in the grid and assign each of them one grid cell.
 * Note that you do not have to have all the people living in the grid.
 * The happiness of each person is calculated as follows:
 * - Introverts start with 120 happiness and lose 30 happiness for each neighbor (introvert or extrovert).
 * - Extroverts start with 40 happiness and gain 20 happiness for each neighbor (introvert or extrovert).
 * Neighbors live in the directly adjacent cells north, east, south, and west of a person's cell.
 * The grid happiness is the sum of each person's happiness. Return the maximum possible grid happiness.
 *
 * Example 1:
 * @link ../../../../resource/MaximizeGridHappiness.jpg
 * Input: m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
 * Output: 240
 * Explanation: Assume the grid is 1-indexed with coordinates (row, column).
 * We can put the introvert in cell (1,1) and put the extroverts in cells (1,3) and (2,3).
 * - Introvert at (1,1) happiness: 120 (starting happiness) - (0 * 30) (0 neighbors) = 120
 * - Extrovert at (1,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
 * - Extrovert at (2,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
 * The grid happiness is 120 + 60 + 60 = 240.
 * The above figure shows the grid in this example with each person's happiness.
 * The introvert stays in the light green cell while the extroverts live on the light purple cells.
 *
 * Example 2:
 * Input: m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
 * Output: 260
 * Explanation: Place the two introverts in (1,1) and (3,1) and the extrovert at (2,1).
 * - Introvert at (1,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
 * - Extrovert at (2,1) happiness: 40 (starting happiness) + (2 * 20) (2 neighbors) = 80
 * - Introvert at (3,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
 * The grid happiness is 90 + 80 + 90 = 260.
 *
 * Example 3:
 * Input: m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
 * Output: 240
 *
 * Constraints:
 * 1. 1 <= m, n <= 5
 * 2. 0 <= introvertsCount, extrovertsCount <= min(m * n, 6)
 */
public class MaximizeGridHappiness {
    public static void main(String[] args) {
        System.out.println(getMaxGridHappiness(2, 3, 1, 2));
        System.out.println(getMaxGridHappiness(3, 1, 2, 1));
    }

    /**
     * DFS + DP
     * 每个位置有三种状态：0-empty，1-intro，2-extro，只有前 n 个位置与当前位置有关，因为前一个是左侧，前 n 个是上侧
     * 使用五维数组记录 i、j、introvertsCount、extrovertsCount、state，然后进行 DFS
     * 对于每个位置尝试 0-empty，1-intro，2-extro 三种可能情况
     */
    public static int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        int numOfState = (int) Math.pow(3, n), mod = numOfState / 3;
        int[][][][][] dp = new int[m][n][introvertsCount + 1][extrovertsCount + 1][numOfState];
        return dfs(0, 0, introvertsCount, extrovertsCount, 0, dp, m, n, mod);
    }

    private static final int INIT = 0, INTROVERT = 1, EXTROVERT = 2;

    private static int dfs(int i, int j, int introvertsCount, int extrovertsCount, int state,
                           int[][][][][] dp, int m, int n, int mod) {
        if (i == m) {
            return 0;
        }
        if (j == n) {
            return dfs(i + 1, 0, introvertsCount, extrovertsCount, state, dp, m, n, mod);
        }
        if (dp[i][j][introvertsCount][extrovertsCount][state] != 0) {
            return dp[i][j][introvertsCount][extrovertsCount][state];
        }

        // 1 - not place
        int max = dfs(i, j + 1, introvertsCount, extrovertsCount, (state % mod) * 3, dp, m, n, mod);

        int up = state / mod, left = state % 3;
        // 2 - place intro
        if (introvertsCount > 0) {
            int cur = 120;
            if (i > 0 && up != INIT) {
                cur -= 30;
                cur += up == INTROVERT ? -30 : 20;
            }
            if (j > 0 && left != INIT) {
                cur -= 30;
                cur += left == INTROVERT ? -30 : 20;
            }
            int nextState = state;
            nextState %= mod;
            nextState *= 3;
            nextState += INTROVERT;
            max = Math.max(max, cur + dfs(i, j + 1, introvertsCount - 1, extrovertsCount, nextState, dp, m, n, mod));
        }

        // 3 - place extro
        if (extrovertsCount > 0) {
            int cur = 40;
            if (i > 0 && up != INIT) {
                cur += 20;
                cur += up == INTROVERT ? -30 : 20;
            }
            if (j > 0 && left != INIT) {
                cur += 20;
                cur += left == INTROVERT ? -30 : 20;
            }
            int nextState = state;
            nextState %= mod;
            nextState *= 3;
            nextState += EXTROVERT;
            max = Math.max(max, cur + dfs(i, j + 1, introvertsCount, extrovertsCount - 1, nextState, dp, m, n, mod));
        }

        return dp[i][j][introvertsCount][extrovertsCount][state] = max;
    }
}
