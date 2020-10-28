package com.wz.string;

/**
 * Given a string s, return the last substring of s in lexicographical order.
 *
 * Example 1:
 * Input: "abab"
 * Output: "bab"
 * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
 *
 * Example 2:
 * Input: "leetcode"
 * Output: "tcode"
 *
 * Note:
 * 1. 1 <= s.length <= 4 * 10^5
 * 2. s contains only lowercase English letters.
 */
public class LastSubstringInLexicographicalOrder {
    public static void main(String[] args) {
        System.out.println(lastSubstring("abab"));
    }

    /**
     * 因为 s[i...j] 的字典序一定小于 s[i...n-1]，所以问题转换成求 s[i...n-1] 的字典序最大的字符串，遍历 s
     * 若 s[index] > s[maxIndex]，则以 s[index] 开头的字符串字典序一定大于以 s[maxIndex] 开头的字符串，maxIndex=index，index 右移
     * 若 s[index] == s[max_index]，用 beginIndex、maxPos 记录 index、maxIndex，使 index 和 maxPos 继续右移
     * 若 s[index] < s[maxIndex]，index 右移
     */
    public static String lastSubstring(String s) {
        int maxIndex = 0, index = 1, n = s.length();
        while (index < n) {
            if (s.charAt(index) > s.charAt(maxIndex)) {
                maxIndex = index;
                index++;
            } else if (s.charAt(index) == s.charAt(maxIndex)) {
                int beginIndex = index, maxPos = maxIndex;
                while (index < n && maxPos < beginIndex && s.charAt(index) == s.charAt(maxPos)) {
                    index++;
                    maxPos++;
                }
                if (index >= n || maxPos >= beginIndex) {
                    continue;
                }
                if (s.charAt(index) > s.charAt(maxPos)) {
                    maxIndex = beginIndex;
                }
            } else {
                index++;
            }
        }

        return s.substring(maxIndex);
    }
}
