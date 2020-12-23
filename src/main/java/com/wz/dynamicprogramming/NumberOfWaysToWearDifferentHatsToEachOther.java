package com.wz.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are n people and 40 types of hats labeled from 1 to 40.
 * Given a list of list of integers hats, where hats[i] is a list of all hats preferred by the i-th person.
 * Return the number of ways that the n people wear different hats to each other.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: hats = [[3,4],[4,5],[5]]
 * Output: 1
 * Explanation: There is only one way to choose hats given the conditions.
 * First person choose hat 3, Second person choose hat 4 and last one hat 5.
 *
 * Example 2:
 * Input: hats = [[3,5,1],[3,5]]
 * Output: 4
 * Explanation: There are 4 ways to choose hats
 * (3,5), (5,3), (1,3) and (1,5)
 *
 * Example 3:
 * Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
 * Output: 24
 * Explanation: Each person can choose hats labeled from 1 to 4.
 * Number of Permutations of (1,2,3,4) = 24.
 *
 * Constraints:
 * 1. n == hats.length
 * 2. 1 <= n <= 10
 * 3. 1 <= hats[i].length <= 40
 * 4. 1 <= hats[i][j] <= 40
 * 5. hats[i] contains a list of unique integers.
 */
public class NumberOfWaysToWearDifferentHatsToEachOther {
    public static void main(String[] args) {
        List<List<Integer>> hats = new ArrayList<>();
        hats.add(Arrays.asList(3, 5, 1));
        hats.add(Arrays.asList(3, 5));
        System.out.println(numberWays(hats));
    }

    public static int numberWays(List<List<Integer>> hats) {
        int n = hats.size(), mod = 1000000007;
        List<Integer>[] list = new List[40];
        for (int i = 0; i < 40; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < hats.size(); i++) {
            for (int hat : hats.get(i)) {
                list[hat - 1].add(i); // convert to 0-indexed
            }
        }

        int[][] dp = new int[41][1 << n];
        dp[0][0] = 1;

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 1 << n; j++) {
                dp[i + 1][j] = dp[i][j];
                for (int people : list[i]) {
                    int index = 1 << people;
                    if ((j & index) > 0) {
                        dp[i + 1][j] = (dp[i + 1][j] + dp[i][j ^ index]) % mod;
                    }
                }
            }
        }

        return dp[40][(1 << n) - 1];
    }

}
