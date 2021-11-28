package com.wz.dynamicprogramming;

/**
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colours:
 * Red, Yellow or Green while making sure that no two adjacent cells have the same colour
 * (i.e no two cells that share vertical or horizontal sides have the same colour).
 * You are given n the number of rows of the grid.
 * Return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 1
 * Output: 12
 * Explanation: There are 12 possible way to paint the grid as shown:
 * @link ../../../../resource/NumberOfWaysToPaintN3Grid.jpg
 *
 * Example 2:
 * Input: n = 2
 * Output: 54
 *
 * Constraints:
 * 1. n == grid.length
 * 2. grid[i].length == 3
 * 3. 1 <= n <= 5000
 */
public class NumberOfWaysToPaintN3Grid {
    public static void main(String[] args) {
        System.out.println(numOfWays(2));
    }

    /**
     * n+1 的染色方案和第 n 行最后一个染色是否同色有关，于是找到规律
     * 每一个同色的方案会产生5种方案，其中3种同色方案和2种不同色方案；每一个不同色方案会产生2种同色方案和2种非同色方案
     */
    public static int numOfWays(int n) {
        int mod = 1000000007;
        long two = 6, three = 6;
        for (int i = 1; i < n; i++) {
            long newTwo = two * 3 + three * 2;
            long newThree = two * 2 + three * 2;
            two = newTwo % mod;
            three = newThree % mod;
        }
        return (int) ((two + three) % mod);
    }

}
