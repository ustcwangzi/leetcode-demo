package com.wz.other;

/**
 * Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:
 * Find the leftmost occurrence of the substring part and remove it from s.
 * Return s after removing all occurrences of part.
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 * Input: s = "daabcbaabcbc", part = "abc"
 * Output: "dab"
 * Explanation: The following operations are done:
 * - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
 * - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
 * - s = "dababc", remove "abc" starting at index 3, so s = "dab".
 * Now s has no occurrences of "abc".
 *
 * Example 2:
 * Input: s = "axxxxyyyyb", part = "xy"
 * Output: "ab"
 * Explanation: The following operations are done:
 * - s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
 * - s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
 * - s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
 * - s = "axyb", remove "xy" starting at index 1 so s = "ab".
 * Now s has no occurrences of "xy".
 *
 * Constraints:
 * 1. 1 <= s.length <= 1000
 * 2. 1 <= part.length <= 1000
 * 3. s and part consists of lowercase English letters.
 */
public class RemoveAllOccurrencesOfSubstring {
    public static void main(String[] args) {
        System.out.println(removeOccurrences("axxxxyyyyb", "xy"));
    }

    /**
     * 使用 StringBuilder 收集结果，收集过程中是否包含 part，若包含则直接移除
     */
    public static String removeOccurrences(String s, String part) {
        if (s.length() < part.length()) {
            return s;
        }

        StringBuilder builder = new StringBuilder();
        for (char cur : s.toCharArray()) {
            builder.append(cur);
            if (builder.length() < part.length()) {
                continue;
            }
            if (builder.substring(builder.length() - part.length()).equals(part)) {
                builder.delete(builder.length() - part.length(), builder.length());
            }
        }
        return builder.toString();
    }
}
