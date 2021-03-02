package com.wz.greedy;

/**
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.
 * Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.
 *
 * Example 1:
 * Input: s = "annabelle", k = 2
 * Output: true
 * Explanation: You can construct two palindromes using all characters in s.
 * Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 *
 * Example 2:
 * Input: s = "leetcode", k = 3
 * Output: false
 * Explanation: It is impossible to construct 3 palindromes using all the characters of s.
 *
 * Example 3:
 * Input: s = "yzyzyzyzyzyzyzy", k = 2
 * Output: true
 * Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. All characters in s are lower-case English letters.
 * 3. 1 <= k <= 10^5
 */
public class ConstructKPalindromeStrings {
    public static void main(String[] args) {
        System.out.println(canConstruct("leetcode", 3));
        System.out.println(canConstruct("yzyzyzyzyzyzyzy", 2));
    }

    /**
     * 因为一个回文字符串中只能有 0 个或 1 个出现次数为奇数的字符，这个字符必须位于回文字符串的中间
     * 因此，要判断能不能有 k 个回文字符串，就看奇数字符出现的次数是否小于等于 k
     * 分配情况：
     * 如果奇数字符恰好有 k 个，那么拆分出来的每个回文字符串中各分配 1 个
     * 如果奇数字符小于 k 个，那么剩下的回文串中不分配奇数字符，即只由偶数字符构成
     */
    public static boolean canConstruct(String s, int k) {
        if (s.length() <= k) {
            return s.length() == k;
        }

        int[] count = new int[26];
        for (char cur : s.toCharArray()) {
            count[cur - 'a']++;
        }

        int oddCount = 0;
        for (int cur : count) {
            if (cur % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount <= k;
    }
}
