package com.wz.other;

/**
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
 * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 * We want to reformat the string s such that each group contains exactly k characters, except for the first group,
 * which could be shorter than k but still must contain at least one character.
 * Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.
 * Return the reformatted license key.
 *
 * Example 1:
 * Input: s = "5F3Z-2e-9-w", k = 4
 * Output: "5F3Z-2E9W"
 * Explanation: The string s has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 *
 * Example 2:
 * Input: s = "2-5g-3-J", k = 2
 * Output: "2-5G-3J"
 * Explanation: The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of English letters, digits, and dashes '-'.
 * 3. 1 <= k <= 10^4
 */
public class LicenseKeyFormatting {
    public static void main(String[] args) {
        System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(licenseKeyFormatting("2-5g-3-J", 2));
    }

    /**
     * 每四个字符后面跟一个 '-'，每一部分的长度为 k，第一部分长度可以小于 k，另外，字母必须是大写的
     * 由于第一部分可以不为 k，那么可以反过来想，从 s 的尾部往前遍历，每 k 个字符加一个 '-'
     */
    public static String licenseKeyFormatting(String s, int k) {
        StringBuilder builder = new StringBuilder(s.replace("-", ""));
        for (int i = builder.length() - k; i > 0; i -= k) {
            builder.insert(i, '-');
        }
        return builder.toString().toUpperCase();
    }
}
