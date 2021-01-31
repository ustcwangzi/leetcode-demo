package com.wz.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * A happy string is a string that:
 * 1. consists only of letters of the set ['a', 'b', 'c'].
 * 2. s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 *
 * Example 1:
 * Input: n = 1, k = 3
 * Output: "c"
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 *
 * Example 2:
 * Input: n = 1, k = 4
 * Output: ""
 * Explanation: There are only 3 happy strings of length 1.
 *
 * Example 3:
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca",
 * "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 *
 * Constraints:
 * 1. 1 <= n <= 10
 * 2. 1 <= k <= 100
 */
public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {
    public static void main(String[] args) {
        System.out.println(getHappyString(1, 3));
        System.out.println(getHappyString(3, 9));
    }

    private static final char[] CHARS = new char[]{'a', 'b', 'c'};

    /**
     * 与 {@link Permutations} 类似
     * 只是相邻的两个字符不能相等，将所有排列组合收集之后，取第 k 个位置的元素就是最终答案
     */
    public static String getHappyString(int n, int k) {
        List<String> result = new ArrayList<>();
        dfs(n, result, new StringBuilder());
        return k <= result.size() ? result.get(k - 1) : "";
    }

    private static void dfs(int n, List<String> result, StringBuilder builder) {
        if (builder.length() == n) {
            result.add(builder.toString());
            return;
        }

        for (char ch : CHARS) {
            // 相邻字符不能相同
            if (builder.length() > 0 && builder.charAt(builder.length() - 1) == ch) {
                continue;
            }
            builder.append(ch);
            dfs(n, result, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
