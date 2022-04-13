package com.wz.string;

/**
 * You are given an array of strings words and a string pref.
 * Return the number of strings in words that contain pref as a prefix.
 * A prefix of a string s is any leading contiguous substring of s.
 *
 * Example 1:
 * Input: words = ["pay","attention","practice","attend"], pref = "at"
 * Output: 2
 * Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".
 *
 * Example 2:
 * Input: words = ["leetcode","win","loops","success"], pref = "code"
 * Output: 0
 * Explanation: There are no strings that contain "code" as a prefix.
 *
 * Constraints:
 * 1. 1 <= words.length <= 100
 * 2. 1 <= words[i].length, pref.length <= 100
 * 3. words[i] and pref consist of lowercase English letters.
 */
public class CountingWordsWithGivenPrefix {
    public static void main(String[] args) {
        System.out.println(prefixCount(new String[]{"pay", "attention", "practice", "attend"}, "at"));
    }

    /**
     * 遍历数组，判断每个 word 是否 startsWith pref 即可
     */
    public static int prefixCount(String[] words, String pref) {
        int count = 0;
        for (String word : words) {
            count += word.startsWith(pref) ? 1 : 0;
        }
        return count;
    }
}
