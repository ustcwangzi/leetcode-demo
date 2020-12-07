package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a string S, count the number of distinct, non-empty subsequences of S .
 * Since the result may be large, return the answer modulo 10^9 + 7.
 *
 * Example 1:
 * Input: "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 *
 * Example 2:
 * Input: "aba"
 * Output: 6
 * Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
 *
 * Example 3:
 * Input: "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 *
 * Note:
 * 1. S contains only lowercase letters.
 * 2. 1 <= S.length <= 2000
 */
public class DistinctSubsequencesII {
    public static void main(String[] args) {
        System.out.println(distinctSubseqII("aba"));
    }

    /**
     * end[i] 表示以第 i 个字母结束的子序列数目
     * 遍历字符串 S，在所有子序列后面加上 S[i] 可以得到新的子序列，然后加上 S[i] 本身就是以 S[i] 结尾的新的子序列个数
     */
    public static int distinctSubseqII(String S) {
        long[] end = new long[26];
        int mod = 1000000007;
        for (char c : S.toCharArray()) {
            end[c - 'a'] = Arrays.stream(end).sum() % mod + 1;
        }
        return (int) (Arrays.stream(end).sum() % mod);
    }
}
