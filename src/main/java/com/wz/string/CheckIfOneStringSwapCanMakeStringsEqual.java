package com.wz.string;

/**
 * You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two indices
 * in a string (not necessarily different) and swap the characters at these indices.
 * Return true if it is possible to make both strings equal by performing at most one string swap on exactly one of the strings. Otherwise, return false.
 *
 * Example 1:
 * Input: s1 = "bank", s2 = "kanb"
 * Output: true
 * Explanation: For example, swap the first character with the last character of s2 to make "bank".
 *
 * Example 2:
 * Input: s1 = "attack", s2 = "defend"
 * Output: false
 * Explanation: It is impossible to make them equal with one string swap.
 *
 * Constraints:
 * 1. 1 <= s1.length, s2.length <= 100
 * 2. s1.length == s2.length
 * 3. s1 and s2 consist of only lowercase English letters.
 */
public class CheckIfOneStringSwapCanMakeStringsEqual {
    public static void main(String[] args) {
        System.out.println(areAlmostEqual("bank", "kanb"));
        System.out.println(areAlmostEqual("attack", "defend"));
    }

    /**
     * 只能有 0 对或 2 对字符不一致，并且不一致的字符必须要相等
     */
    public static boolean areAlmostEqual(String s1, String s2) {
        // 记录不一致次数和不一致的字符
        int diff = 0, a = 0, b = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (a == 0) {
                    a = s1.charAt(i);
                    b = s2.charAt(i);
                } else if (a != s2.charAt(i) || b != s1.charAt(i)) {
                    return false;
                }
            }
            if (diff > 2) {
                return false;
            }
        }
        return diff == 0 || diff == 2;
    }
}
