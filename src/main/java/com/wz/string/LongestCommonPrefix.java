package com.wz.string;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    /**
     * 遍历数组，遍历过程中记录 commonPrefix
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String commonPrefix = strs[0];
        for (String str : strs) {
            commonPrefix = commonPrefix(commonPrefix, str);
            if ("".equals(commonPrefix)) {
                return "";
            }
        }
        return commonPrefix;
    }

    private static String commonPrefix(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                builder.append(s1.charAt(i));
            } else {
                return builder.toString();
            }
        }
        return builder.toString();
    }
}
