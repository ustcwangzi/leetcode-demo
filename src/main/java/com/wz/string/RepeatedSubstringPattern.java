package com.wz.string;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 *
 * Example 1:
 * Input: "aba"
 * Output: False
 *
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("aba"));
        System.out.println(repeatedSubstringPattern("abcabcabcabc"));
    }

    /**
     * 将 s 和自身连接组成一个新的字符串 str，然后判断 str[1, str.length()-1) 是否包含 s
     * 如果 s 是由可连续的子串组成，那么将其重复一遍后，重复子串的数量是原来的2倍，再将其去头去尾，那么中间至少会存在一个完整的 s，
     * 如果s是由单字母组成，就会存在多个s。反之，如果 s 不是由连续子串组成，在进行上面的操作后，中间部分是不可能存在 s 的，因为不具备连续性，
     * 再重复一遍的新字符串肯定也不具备连续性
     */
    public static boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
