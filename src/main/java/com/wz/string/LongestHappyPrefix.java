package com.wz.string;

/**
 * A string is called a happy prefix if is a non-empty prefix which is also a suffix (excluding itself).
 * Given a string s. Return the longest happy prefix of s . Return an empty string if no such prefix exists.
 *
 * Example 1:
 * Input: s = "level"
 * Output: "l"
 * Explanation: s contains 4 prefix excluding itself ("l", "le", "lev", "leve"),
 *              and suffix ("l", "el", "vel", "evel"). The largest prefix which is also suffix is given by "l".
 *
 * Example 2:
 * Input: s = "ababab"
 * Output: "abab"
 * Explanation: "abab" is the largest prefix which is also suffix. They can overlap in the original string.
 *
 * Example 3:
 * Input: s = "leetcodeleet"
 * Output: "leet"
 *
 * Example 4:
 * Input: s = "a"
 * Output: ""
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s contains only lowercase English letters.
 */
public class LongestHappyPrefix {
    public static void main(String[] args) {
        System.out.println(longestPrefix("leetcodeleet"));
    }

    /**
     * 给定一个字符串，其长度为k的前缀和长度为k后缀有可能相等，问最大的k对应的那个前缀是什么
     * 本质上是求KMP算法中的next数组
     */
    public static String longestPrefix(String s) {
        // 因为要求的是s的最长相等前后缀的长度，所以要多开一个长度
        int[] next = new int[s.length() + 1];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < s.length()) {
            while (j != -1 && s.charAt(i) != s.charAt(j)) {
                j = next[j];
            }
            i++;
            j++;
            next[i] = j;
        }

        return s.substring(0, next[s.length()]);
    }
}
