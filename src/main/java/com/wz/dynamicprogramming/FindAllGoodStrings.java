package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given the strings s1 and s2 of size n, and the string evil. Return the number of good strings.
 * A good string has size n, it is alphabetically greater than or equal to s1, it is alphabetically smaller than or equal to s2,
 * and it does not contain the string evil as a substring. Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 2, s1 = "aa", s2 = "da", evil = "b"
 * Output: 51
 * Explanation: There are 25 good strings starting with 'a': "aa","ac","ad",...,"az". Then there are 25 good strings
 * starting with 'c': "ca","cc","cd",...,"cz" and finally there is one good string starting with 'd': "da".
 *
 * Example 2:
 * Input: n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
 * Output: 0
 * Explanation: All strings greater than or equal to s1 and smaller than or equal to s2 start with the prefix "leet",
 * therefore, there is not any good string.
 *
 * Example 3:
 * Input: n = 2, s1 = "gx", s2 = "gz", evil = "x"
 * Output: 2
 *
 * Constraints:
 * 1. s1.length == n
 * 2. s2.length == n
 * 3. s1 <= s2
 * 4. 1 <= n <= 500
 * 5. 1 <= evil.length <= 50
 * 6. All strings consist of lowercase English letters.
 */
public class FindAllGoodStrings {

    int[] pre;
    long[][][][] dp;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        if (s1.equals(s2)) {
            if (s1.contains(evil)) {
                return 0;
            }
            return 1;
        }

        pre = pre(evil);
        dp = new long[s1.length()][s2.length()][2][2];

        for (long[][][] longs : dp) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    Arrays.fill(longs[j][k], -1);
                }
            }
        }
        return (int) (dfs(evil, s1, s2, 0, 0, 1, 1));
    }

    public long dfs(String evil, String s1, String s2, int i, int j, int state1, int state2) {
        if (j == evil.length()) {
            return 0;
        }
        if (i == s1.length()) {
            return 1;
        }

        if (dp[i][j][state1][state2] != -1) {
            return dp[i][j][state1][state2];
        }

        int mod = 1000000007;
        long res = 0;
        char small = s1.charAt(i), big = s2.charAt(i);

        for (int x = 0; x < 26; x++) {
            char c = (char) (x + 'a');
            int news1 = state1;
            int news2 = state2;

            if (state1 == 1 && state2 == 0) {
                if (c < small) continue;
                if (c != small) news1 = 0;
            } else if (state1 == 0 && state2 == 1) {
                if (c > big) continue;
                if (c != big) news2 = 0;
            } else if (state1 == 1 && state2 == 1) {
                if (c < small || c > big) continue;
                if (c != small) news1 = 0;
                if (c != big) news2 = 0;
            }


            //kmp part
            int evinIndex = j;
            if (c == evil.charAt(evinIndex)) {
                evinIndex++;
            } else if (evinIndex != 0) {
                while (evinIndex != 0 && evil.charAt(evinIndex) != c) {
                    evinIndex = pre[evinIndex - 1];
                }
                if (c == evil.charAt(evinIndex)) {
                    evinIndex++;
                }
            }

            res += dfs(evil, s1, s2, i + 1, evinIndex, news1, news2);
            res %= mod;
        }
        dp[i][j][state1][state2] = res;
        return res;
    }

    public int[] pre(String p) {
        int[] pre = new int[p.length()];
        int l = 0, r = 1;
        while (r < p.length()) {
            if (p.charAt(l) == p.charAt(r)) {
                pre[r] = l + 1;
                l++;
                r++;
            } else {
                if (l == 0) r++;
                else l = pre[l - 1];
            }
        }
        return pre;
    }
}
