package com.wz.string;

/**
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t  (t concatenated with itself 1 or more times)
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 * Example 1:
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 *
 * Example 2:
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 *
 * Example 3:
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 *
 * Constraints:
 * 1. 1 <= str1.length <= 1000
 * 2. 1 <= str2.length <= 1000
 * 3. str1 and str2 consist of English uppercase letters.
 */
public class GreatestCommonDivisorOfStrings {
    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABABAB", "AB"));
        System.out.println(gcdOfStrings("LEET", "CODE"));
    }

    /**
     * 找两个字符串的最长公共子串
     * 假设：str1.length > str2.length
     * 因为是公共子串，所以 str2 一定可以和 str1 前面一部分匹配上，否则不存在公共子串
     * 比较 str2 和 str1 的 0～str2.length()-1 部分，若不同，则直接返回""，不存在公共子串
     * 若相同，继续比较 str2 和 str1 的剩下部分，即递归 gcd(str2, str1.substring(str2.length))
     * 同时，当 str1 == str2 时，检查 str1.equals(str2)
     */
    public static String gcdOfStrings(String str1, String str2) {
        return gcd(str1, str2);
    }

    private static String gcd(String a, String b) {
        if (a.length() > b.length()) {
            for (int i = 0; i < b.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    return "";
                }
            }
            return gcd(a.substring(b.length()), b);
        } else if (a.length() < b.length()) {
            return gcd(b, a);
        } else {
            return a.equals(b) ? a : "";
        }
    }
}
