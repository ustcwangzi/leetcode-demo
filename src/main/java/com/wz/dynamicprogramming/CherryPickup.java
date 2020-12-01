package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * In a N x N grid representing a field of cherries, each cell is one of three possible integers.
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass through;
 * -1 means the cell contains a thorn that blocks your way.
 * Your task is to collect maximum number of cherries possible by following the rules below:
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 *
 * Example 1:
 * Input: grid =
 * [[0, 1, -1],
 *  [1, 0, -1],
 *  [1, 1,  1]]
 * Output: 5
 * Explanation:
 * The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 *
 * Note:
 * 1. grid is an N by N 2D array, with 1 <= N <= 50.
 * 2. Each grid[i][j] is an integer in the set {-1, 0, 1}.
 * 3. It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 */
public class CherryPickup {
    public static void main(String[] args) {
        System.out.println(cherryPickup(new int[][]{
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        }));
    }

    /**
     * dp[i][j] 表示到达 (i,j) 位置并返回起点时能捡到的最大樱桃数，但是新的问题就来了，樱桃只有一个，只能捡一次，去程捡了，返程就不能再捡了，
     * 如何才能避免重复计算呢？只有i和j是不够的，其只能定义去程的位置，还需要pg，来定义返程的位置，
     * 那么重现关系Recurrence Relations就变成了 T(i, j, p, g)，有分别两种方式离开(i, j)和(p, g)，从终点往起点遍历，那么就有4种情况：
     * Case 1: (0, 0) ==> (i-1, j) ==> (i, j); (p, q) ==> (p-1, q) ==> (0, 0)
     * Case 2: (0, 0) ==> (i-1, j) ==> (i, j); (p, q) ==> (p, q-1) ==> (0, 0)
     * Case 3: (0, 0) ==> (i, j-1) ==> (i, j); (p, q) ==> (p-1, q) ==> (0, 0)
     * Case 4: (0, 0) ==> (i, j-1) ==> (i, j); (p, q) ==> (p, q-1) ==> (0, 0)
     * 根据定义，有：
     * Case 1 is equivalent to T(i-1, j, p-1, q) + grid[i][j] + grid[p][q];
     * Case 2 is equivalent to T(i-1, j, p, q-1) + grid[i][j] + grid[p][q];
     * Case 3 is equivalent to T(i, j-1, p-1, q) + grid[i][j] + grid[p][q];
     * Case 4 is equivalent to T(i, j-1, p, q-1) + grid[i][j] + grid[p][q];
     * 因此，重现关系可以写作：
     * T(i, j, p, q) = grid[i][j] + grid[p][q] + max{T(i-1, j, p-1, q), T(i-1, j, p, q-1), T(i, j-1, p-1, q), T(i, j-1, p, q-1)}
     * 为了避免重复计算，希望 grid[i][j] 和 grid[p][g] 不出现在T(i-1, j, p-1, q), T(i-1, j, p, q-1), T(i, j-1, p-1, q) 和 T(i, j-1, p, q-1)中的任意一个上。
     * 显而易见的是(i, j)不会出现在(0, 0) ==> (i-1, j) 或 (0, 0) ==> (i, j-1) 的路径上，
     * 同理，(p, g) 也不会出现在 (p-1, q) ==> (0, 0) 或 (p, q-1) ==> (0, 0) 的路径上。
     * 因此，需要保证(i, j) 不会出现在 (p-1, q) ==> (0, 0) 或 (p, q-1) ==> (0, 0) 的路径上，
     * 同时 (p, g)不会出现在(0, 0) ==> (i-1, j) 或 (0, 0) ==> (i, j-1) 的路径上，怎么做呢？
     * 观察到(0, 0) ==> (i-1, j) 和 (0, 0) ==> (i, j-1) 的所有点都在矩形 [0, 0, i, j] 中（除了右下角点(i, j)点），
     * 所以只要 (p, g) 不在矩形 [0, 0, i, j] 中就行了，注意(p, g) 和 (i, j) 是有可能重合了，这种情况特殊处理一下就行了。
     * 同理， (i, j) 也不能在矩形 [0, 0, p, g] 中，那么以下三个条件中需要满足一个：
     * i < p && j > q
     * i == p && j == q
     * i > p && j < q
     * 为了满足上述条件，我们希望当 i 或 p 增加的时候，j 或 q 减小，那么可以有这个等式:
     * k = i + j = p + q
     * 其中k为从起点开始走的步数，所以可以用 T(k, i, p)  来代替 T(i, j, p, g)，那么重现关系式就变成了：
     * T(k, i, p) = grid[i][k-i] + grid[p][k-p] + max{T(k-1, i-1, p-1), T(k-1, i-1, p), T(k-1, i, p-1), T(k-1, i, p)}.
     * 当 i == p 时，grid[i][k-i] 和 grid[p][k-p] 就相等了，此时只能加一个。注意到 i, j, p, q 的范围是 [0, n)，
     * 意味着k只能在范围 [0, 2n - 1) 中， 初始化时 T(0, 0, 0) = grid[0][0]。
     * 这里的重现关系T虽然是三维的，但是可以用二维dp数组来实现，因为第k步的值只依赖于第k-1步的情况
     */
    public static int cherryPickup(int[][] grid) {
        int n = grid.length, mx = 2 * n - 1;
        int[][] dp = new int[n][n];
        for (int[] array : dp) {
            Arrays.fill(array, -1);
        }

        dp[0][0] = grid[0][0];
        for (int k = 1; k < mx; ++k) {
            for (int i = n - 1; i >= 0; --i) {
                for (int p = n - 1; p >= 0; --p) {
                    int j = k - i, q = k - p;
                    if (j < 0 || j >= n || q < 0 || q >= n || grid[i][j] < 0 || grid[p][q] < 0) {
                        dp[i][p] = -1;
                        continue;
                    }
                    if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
                    if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
                    if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);
                    if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0);
                }
            }
        }
        return Math.max(dp[n - 1][n - 1], 0);
    }
}
