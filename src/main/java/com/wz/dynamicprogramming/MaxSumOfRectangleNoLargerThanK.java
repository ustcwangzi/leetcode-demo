package com.wz.dynamicprogramming;

import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 *
 * Example:
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 *              and 2 is the max number no larger than k (k = 2).
 *
 * Note:
 * 1. The rectangle inside the matrix must have an area > 0.
 * 2. What if the number of rows is much larger than the number of columns?
 */
public class MaxSumOfRectangleNoLargerThanK {
    public static void main(String[] args) {
        System.out.println(maxSumSubmatrix(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));
    }

    /**
     * 把二维数组按行或列拆成多个一维数组，然后利用一维数组的累加和来找符合要求的数字，用 lower_bound 来加快搜索速度
     * 建立一个 TreeSet，然后开始先放个0进去，因为要找 lower_bound(curSum - k)，当 curSum 和 k 相等时，0就可以被返回了
     * 由于对于一维数组建立了累积和，那么 sum[i,j] = sum[i] - sum[j]，其中 sums[i,j] 就是目标子数组需要其和小于等于k，
     * 然后 sums[j] 是 curSum，而 sum[i] 就是要找值，当使用二分搜索法找 sum[i] 时，sum[i] 的和需要大于等于 sum[j] - k，
     * 所以也可以使用 lower_bound 来找，即 TreeSet 的 ceiling
     */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] preSum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i][j - 1] + matrix[i][j - 1];
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                int sum = 0;
                set.add(sum);

                for (int r = 0; r < m; r++) {
                    sum += preSum[r][j + 1] - preSum[r][i];
                    Integer prevSum = set.ceiling(sum - k);
                    if (prevSum != null) {
                        result = Math.max(sum - prevSum, result);
                    }
                    set.add(sum);
                }
            }
        }

        return result;
    }
}
