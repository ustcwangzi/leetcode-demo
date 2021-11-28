package com.wz.dynamicprogramming;

/**
 * There is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:
 * You will pick any pizza slice.
 * Your friend Alice will pick next slice in anti clockwise direction of your pick.
 * Your friend Bob will pick next slice in clockwise direction of your pick.
 * Repeat until there are no more slices of pizzas.
 * Sizes of Pizza slices is represented by circular array slices in clockwise direction.
 * Return the maximum possible sum of slice sizes which you can have.
 *
 * Example 1:
 * @link ../../../../resource/PizzaWith3nSlices1.jpg
 * Input: slices = [1,2,3,4,5,6]
 * Output: 10
 * Explanation: Pick pizza slice of size 4, Alice and Bob will pick slices with size 3 and 5 respectively. Then Pick slices with size 6, finally Alice and Bob will pick slice of size 2 and 1 respectively. Total = 4 + 6.
 *
 * Example 2:
 * @link ../../../../resource/PizzaWith3nSlices2.jpg
 * Input: slices = [8,9,8,6,1,1]
 * Output: 16
 * Output: Pick pizza slice of size 8 in each turn. If you pick slice with size 9 your partners will pick slices of size 8.
 *
 * Constraints:
 * 1. 1 <= slices.length <= 500
 * 2. slices.length % 3 == 0
 * 3. 1 <= slices[i] <= 1000
 */
public class PizzaWith3nSlices {
    public static void main(String[] args) {
        System.out.println(maxSizeSlices(new int[]{1, 2, 3, 4, 5, 6}));
    }

    /**
     * 题目可以转换为，取任意 n/3 个不相邻的数字的最大和
     * dp[i][j] 表示取 i 块最后一块序列为 j 的最大值，由于第一块和最后一块不能同时取，所以需要计算两次
     */
    public static int maxSizeSlices(int[] slices) {
        int n = slices.length, select = n / 3;
        int[][] dp1 = new int[select + 1][n], dp2 = new int[select + 1][n];
        dp1[1][0] = slices[0];
        dp2[1][1] = slices[1];
        for (int i = 1; i <= select; i++) {
            for (int j = 2; j < n; j++) {
                for (int k = 0; k < j - 1; k++) {
                    dp1[i][j] = Math.max(dp1[i][j], dp1[i - 1][k] + slices[j]);
                    dp2[i][j] = Math.max(dp2[i][j], dp2[i - 1][k] + slices[j]);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            result = Math.max(result, dp1[select][i]);
        }
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp2[select][i]);
        }
        return result;
    }
}
