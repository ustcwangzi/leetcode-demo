package com.wz.dynamicprogramming;

/**
 * Given an array A of strings, find any smallest string that contains each string in A as a substring.
 * We may assume that no string in A is substring of another string in A.
 *
 * Example 1:
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 *
 * Example 2:
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 *
 * Note:
 * 1. 1 <= A.length <= 12
 * 2. 1 <= A[i].length <= 20
 */
public class FindTheShortestSuperstring {
    public static void main(String[] args) {
        System.out.println(shortestSuperstring(new String[]{"alex", "loves", "leetcode"}));
    }

    public static String shortestSuperstring(String[] A) {
        String[][] dp = new String[A.length][5000];
        String[][] lookup = new String[A.length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (i == j) {
                    continue;
                }
                int k = 1, idx = 0;
                while (k <= A[j].length()) {
                    if (A[i].endsWith(A[j].substring(0, k))) {
                        idx = k;
                    }
                    k++;
                }
                lookup[i][j] = A[j].substring(idx);
            }
        }

        String result = "";
        int minLength = Integer.MAX_VALUE / 2, bitmask = 0;

        for (int i = 0; i < A.length; i++) {
            String tmp = A[i] + superString(A, i, bitmask | (1 << i), dp, lookup);
            if (tmp.length() < minLength) {
                minLength = tmp.length();
                result = tmp;
            }
        }
        return result;
    }

    private static String superString(String[] A, int last, int bitmask, String[][] dp, String[][] lookup) {
        String result = "";
        int minLength = Integer.MAX_VALUE / 2;
        if (dp[last][bitmask] != null)
            return dp[last][bitmask];

        for (int i = 0; i < A.length; i++) {
            if ((bitmask & (1 << i)) != 0) {
                continue;
            }
            String tmp = lookup[last][i] + superString(A, i, bitmask | (1 << i), dp, lookup);
            if (tmp.length() < minLength) {
                minLength = tmp.length();
                result = tmp;
            }
        }
        return dp[last][bitmask] = result;
    }
}
