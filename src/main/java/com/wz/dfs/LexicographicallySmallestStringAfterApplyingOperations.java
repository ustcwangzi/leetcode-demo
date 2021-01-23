package com.wz.dfs;

import java.util.*;

/**
 * You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.
 * You can apply either of the following two operations any number of times and in any order on s:
 * Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0. For example, if s = "3456" and a = 5, s becomes "3951".
 * Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".
 * Return the lexicographically smallest string you can obtain by applying the above operations any number of times on s.
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ,
 * string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example,
 * "0158" is lexicographically smaller than "0190" because the first position they differ is at the third letter, and '5' comes before '9'.
 *
 * Example 1:
 * Input: s = "5525", a = 9, b = 2
 * Output: "2050"
 * Explanation: We can apply the following operations:
 * Start:  "5525"
 * Rotate: "2555"
 * Add:    "2454"
 * Add:    "2353"
 * Rotate: "5323"
 * Add:    "5222"
 * Add:    "5121"
 * Rotate: "2151"
 * Add:    "2050"
 * There is no way to obtain a string that is lexicographically smaller then "2050".
 */
public class LexicographicallySmallestStringAfterApplyingOperations {
    public static void main(String[] args) {
        System.out.println(findLexSmallestString("5525", 9, 2));
    }

    /**
     * 搜集所有的string，然后求字典序最小的即可
     */
    public static String findLexSmallestString(String s, int a, int b) {
        Set<String> set = new HashSet<>();
        dfs(s, a, b, set);
        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        return list.get(0);
    }

    private static void dfs(String s, int a, int b, Set<String> set) {
        if (set.contains(s)) {
            return;
        }
        set.add(s);
        dfs(s.substring(s.length() - b) + s.substring(0, s.length() - b), a, b, set);
        char[] c = s.toCharArray();
        for (int i = 1; i < s.length(); i += 2) {
            int val = c[i] - '0';
            val = (val + a) % 10;
            c[i] = (char) (val + '0');
        }
        dfs(new String(c), a, b, set);
    }
}
