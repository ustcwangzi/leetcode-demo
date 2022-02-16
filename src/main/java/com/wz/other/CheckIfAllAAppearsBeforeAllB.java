package com.wz.other;

/**
 * Given a string s consisting of only the characters 'a' and 'b', return true if every 'a' appears before every 'b' in the string. Otherwise, return false.
 *
 *
 *
 * Example 1:
 * Input: s = "aaabbb"
 * Output: true
 * Explanation:
 * The 'a's are at indices 0, 1, and 2, while the 'b's are at indices 3, 4, and 5.
 * Hence, every 'a' appears before every 'b' and we return true.
 *
 * Example 2:
 * Input: s = "abab"
 * Output: false
 * Explanation:
 * There is an 'a' at index 2 and a 'b' at index 1.
 * Hence, not every 'a' appears before every 'b' and we return false.
 *
 * Example 3:
 * Input: s = "bbb"
 * Output: true
 * Explanation:
 * There are no 'a's, hence, every 'a' appears before every 'b' and we return true.
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s[i] is either 'a' or 'b'.
 */
public class CheckIfAllAAppearsBeforeAllB {
    public static void main(String[] args) {
        System.out.println(checkString("aaabbb"));
        System.out.println(checkString("abab"));
    }

    /**
     * 遍历字符串，使用 boolean 记录是否已出现 b，若已出现并且当前字符为 a，说明 b 出现在了 a 前面，返回 false
     */
    public static boolean checkString(String s) {
        boolean bAppear = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                bAppear = true;
            } else if (bAppear) {
                return false;
            }
        }
        return true;
    }
}
