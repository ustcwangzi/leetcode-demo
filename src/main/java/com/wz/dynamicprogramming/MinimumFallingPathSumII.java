package com.wz.dynamicprogramming;

/**
 * Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr,
 * such that no two elements chosen in adjacent rows are in the same column.
 * Return the minimum sum of a falling path with non-zero shifts.
 *
 * Example 1:
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation:
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 *
 * Constraints:
 * 1. 1 <= arr.length == arr[i].length <= 200
 * 2. -99 <= arr[i][j] <= 99
 */
public class MinimumFallingPathSumII {
    public static void main(String[] args) {
        System.out.println(minFallingPathSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }));
    }

    /**
     * dp[i][j] 表示第 i 行取第 j 个元素时，在0～i行区间内获得的最小值，
     * 那么 dp[i][j] = min{dp[i][j],dp[i-1][k] + arr[i][j]}，其中j != k，这样会超时。
     * 对于任意一个 j，在 dp[i-1] 中只要找出最小值即可，当然最小值的所在的列不能和 j 相同。那么只需要记录 dp[i-1] 行中的最小值和次小值，
     * 如果最小值的下标和 j 相同就取次小值，否则取最小值
     */
    public static int minFallingPathSum(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            int min = Integer.MAX_VALUE, secondMin = min;
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i - 1][j] <= min) {
                    secondMin = min;
                    min = arr[i - 1][j];
                }
                if (arr[i - 1][j] < secondMin && arr[i - 1][j] > min) {
                    secondMin = arr[i - 1][j];
                }
            }

            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i - 1][j] == min) {
                    arr[i][j] += secondMin;
                } else if (arr[i - 1][j] == secondMin) {
                    arr[i][j] += min;
                } else {
                    arr[i][j] += min;
                }
            }
        }

        int[] lastRow = arr[arr.length - 1];
        int result = Integer.MAX_VALUE;
        for (int num : lastRow) {
            result = Math.min(result, num);
        }
        return result;
    }
}
