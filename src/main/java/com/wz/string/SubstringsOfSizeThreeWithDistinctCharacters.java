package com.wz.string;

/**
 * A string is good if there are no repeated characters.
 * Given a string s, return the number of good substrings of length three in s.
 * Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 * Input: s = "xyzzaz"
 * Output: 1
 * Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
 * The only good substring of length 3 is "xyz".
 *
 * Example 2:
 * Input: s = "aababcabc"
 * Output: 4
 * Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
 * The good substrings are "abc", "bca", "cab", and "abc".
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s consists of lowercase English letters.
 */
public class SubstringsOfSizeThreeWithDistinctCharacters {
    public static void main(String[] args) {
        System.out.println(countGoodSubstrings("xyzzaz"));
        System.out.println(countGoodSubstrings("aababcabc"));
    }

    /**
     * 遍历 s，使用 a、b、c 记录最近的三个字符，判断是否相等
     */
    public static int countGoodSubstrings(String s) {
        if (s.length() < 3) {
            return 0;
        }

        int count = 0;
        for (int i = 2; i < s.length(); i++) {
            char a = s.charAt(i - 2), b = s.charAt(i - 1), c = s.charAt(i);
            if (a != b && a != c && b != c) {
                count++;
            }
        }
        return count;
    }
}
