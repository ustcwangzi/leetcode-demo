package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz",
 * so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s.
 * In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 *
 * Example 1:
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 *
 * Example 2:
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 */
public class UniqueSubstringsInWraparoundString {
    public static void main(String[] args) {
        System.out.println(findSubstringInWraproundString("zab"));
    }

    /**
     * 满足题意的子串要么是单一的字符，要么是按字母顺序的子串，以 abcd 为例，以 d 结尾的子串有 abcd, bcd, cd, d，
     * 可以发现 bcd 或者 cd 这些以 d 结尾的子串都包含在 abcd 中，即以某个字符结束的最大字符串包含其他以该字符结束的字符串的所有子字符串，
     * 那么题目就可以转换为分别求出以每个字符 (a-z) 为结束字符的最长连续字符串就行了，用 dp 记录下来，最后在求出 dp 的所有数字之和
     */
    public static int findSubstringInWraproundString(String p) {
        // 以每个字符结尾的最长连续子串长度
        int[] dp = new int[26];
        int length = 0;
        for (int i = 0; i < p.length(); i++) {
            // 连续子串
            if (i > 0 && (p.charAt(i) == p.charAt(i - 1) + 1 || p.charAt(i - 1) == p.charAt(i) + 25)) {
                length++;
            } else {
                length = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], length);
        }
        return Arrays.stream(dp).sum();
    }
}
