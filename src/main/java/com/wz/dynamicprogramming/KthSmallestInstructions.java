package com.wz.dynamicprogramming;

/**
 * Bob is standing at cell (0, 0), and he wants to reach destination: (row, column). He can only travel right and down. You are going to help Bob by providing instructions for him to reach destination.
 * The instructions are represented as a string, where each character is either:
 * - 'H', meaning move horizontally (go right), or
 * - 'V', meaning move vertically (go down).
 * Multiple instructions will lead Bob to destination. For example, if destination is (2, 3), both "HHHVV" and "HVHVH" are valid instructions.
 * However, Bob is very picky. Bob has a lucky number k, and he wants the kth lexicographically smallest instructions that will lead him to destination. k is 1-indexed.
 * Given an integer array destination and an integer k, return the kth lexicographically smallest instructions that will take Bob to destination.
 *
 * Example 1:
 * @link ../../../../resource/KthSmallestInstructions1.jpg
 * Input: destination = [2,3], k = 1
 * Output: "HHHVV"
 * Explanation: All the instructions that reach (2, 3) in lexicographic order are as follows:
 * ["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
 *
 * Example 2:
 * @link ../../../../resource/KthSmallestInstructions2.jpg
 * Input: destination = [2,3], k = 2
 * Output: "HHVHV"
 *
 * Example 3:
 * @link ../../../../resource/KthSmallestInstructions3.jpg
 * Input: destination = [2,3], k = 3
 * Output: "HHVVH"
 *
 * Constraints:
 * 1. destination.length == 2
 * 2. 1 <= row, column <= 15
 * 3. 1 <= k <= nCr(row + column, row), where nCr(a, b) denotes a choose b.
 */
public class KthSmallestInstructions {
    public static void main(String[] args) {
        System.out.println(kthSmallestPath(new int[]{2, 3}, 1));
        System.out.println(kthSmallestPath(new int[]{2, 3}, 2));
        System.out.println(kthSmallestPath(new int[]{2, 3}, 3));
    }

    /**
     * 从起点到终点的 H、V 个数是固定的，要求第 k 小的字典序，就是对 H、V 进行排列组合
     * dp[i][j] 表示 H、V 的组合形式个数，因为 H 比 V 小，所以先考虑 H 的情况
     * 若 dp[i][j + 1] >= k，说明此时不能增加 V，要继续使用 H，否则要是用 V，同时 k 减少 dp[i][j + 1]
     */
    public static String kthSmallestPath(int[] destination, int k) {
        int m = destination[0], n = destination[1];
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                dp[i][j] = (i == m || j == n) ? 1 : dp[i][j + 1] + dp[i + 1][j];
            }
        }

        StringBuilder builder = new StringBuilder();
        int i = 0, j = 0;
        while (i < m || j < n) {
            if (i == m) {
                builder.append('H');
                j++;
                continue;
            }
            if (j == n) {
                builder.append('V');
                i++;
                continue;
            }
            if (dp[i][j + 1] >= k) {
                builder.append('H');
                j++;
            } else {
                builder.append('V');
                k -= dp[i][j + 1];
                i++;
            }
        }
        return String.valueOf(builder);
    }
}
