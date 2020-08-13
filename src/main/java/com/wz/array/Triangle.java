package com.wz.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> group = new ArrayList<>();
        group.add(2);
        triangle.add(group);

        group = new ArrayList<>();
        group.add(3);
        group.add(4);
        triangle.add(group);

        group = new ArrayList<>();
        group.add(6);
        group.add(5);
        group.add(7);
        triangle.add(group);

        group = new ArrayList<>();
        group.add(4);
        group.add(1);
        group.add(8);
        group.add(3);
        triangle.add(group);

        System.out.println(minimumTotal(triangle));
    }

    /**
     * 动态规划
     * 初始化dp数组大小为triangle最后一行数据的个数，然后逐层向上扫描
     * 递推公式为：dp[j] = min(dp[j],dp[j+1])+triangle[i][j]
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if (m <= 0) {
            return 0;
        }

        int[] dp = new int[m];
        List<Integer> lastRow = triangle.get(m - 1);
        // dp初始化为triangle最后一行数据
        for (int i = 0; i < m; i++) {
            dp[i] = lastRow.get(i);
        }

        // 逐层向上扫描，更新dp
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
