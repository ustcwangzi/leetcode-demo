package com.wz.dynamicprogramming;

/**
 * Given a rectangle of size n x m, find the minimum number of integer-sided squares that tile the rectangle.
 *
 * Example 1:
 * @link ../../../../resource/TilingRectangleWithTheFewestSquares1.jpg
 * Input: n = 2, m = 3
 * Output: 3
 * Explanation: 3 squares are necessary to cover the rectangle.
 * 2 (squares of 1x1)
 * 1 (square of 2x2)
 *
 * Example 2:
 * @link ../../../../resource/TilingRectangleWithTheFewestSquares2.jpg
 * Input: n = 5, m = 8
 * Output: 5
 *
 * Constraints:
 * 1. 1 <= n <= 13
 * 2. 1 <= m <= 13
 */
public class TilingRectangleWithTheFewestSquares {
    public static void main(String[] args) {
        System.out.println(tilingRectangle(2, 3));
        System.out.println(tilingRectangle(5, 8));
    }

    public static int tilingRectangle(int n, int m) {
        if (n > m) return tilingRectangle(m, n);
        return helper(n, m, new int[n + 1][m + 1]);
    }

    private static int helper(int n, int m, int[][] cache) {
        if (n > m) return helper(m, n, cache);
        if (n == 0) return 0;
        if (n == m) return 1;
        if (n == 1) return m;
        if (cache[n][m] > 0) return cache[n][m];
        int count = Integer.MAX_VALUE;
        for (int size = 1; size <= n; size++) {
            // two ways of dividing via 1 square + 2 rectangles.
            count = Math.min(count, 1 + helper(n - size, m, cache) + helper(size, m - size, cache));
            count = Math.min(count, 1 + helper(n, m - size, cache) + helper(n - size, size, cache));
            // ways of dividing via 2 squares + 3 rectangles.
            for (int size2 = n - size + 1; size2 < m - size && size2 < n; size2++) {
                count = Math.min(count, 2 + helper(n - size, m - size2, cache)
                        + helper(n - size2, m - size, cache) +
                        +helper(size + size2 - n, m - size - size2, cache));
            }
        }
        cache[n][m] = count;
        return count;
    }
}
