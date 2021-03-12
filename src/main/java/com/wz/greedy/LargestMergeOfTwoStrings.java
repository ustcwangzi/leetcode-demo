package com.wz.greedy;

/**
 * You are given two strings word1 and word2. You want to construct a string merge in the following way:
 * while either word1 or word2 are non-empty, choose one of the following options:
 * 1. If word1 is non-empty, append the first character in word1 to merge and delete it from word1.
 *    For example, if word1 = "abc" and merge = "dv", then after choosing this operation, word1 = "bc" and merge = "dva".
 * 2. If word2 is non-empty, append the first character in word2 to merge and delete it from word2.
 *    For example, if word2 = "abc" and merge = "", then after choosing this operation, word2 = "bc" and merge = "a".
 * Return the lexicographically largest merge you can construct.
 * A string a is lexicographically larger than a string b (of the same length) if in the first position where a and b differ,
 * a has a character strictly larger than the corresponding character in b. For example, "abcd" is lexicographically
 * larger than "abcc" because the first position they differ is at the fourth character, and d is greater than c.
 *
 * Example 1:
 * Input: word1 = "cabaa", word2 = "bcaaa"
 * Output: "cbcabaaaaa"
 * Explanation: One way to get the lexicographically largest merge is:
 * - Take from word1: merge = "c", word1 = "abaa", word2 = "bcaaa"
 * - Take from word2: merge = "cb", word1 = "abaa", word2 = "caaa"
 * - Take from word2: merge = "cbc", word1 = "abaa", word2 = "aaa"
 * - Take from word1: merge = "cbca", word1 = "baa", word2 = "aaa"
 * - Take from word1: merge = "cbcab", word1 = "aa", word2 = "aaa"
 * - Append the remaining 5 a's from word1 and word2 at the end of merge.
 *
 * Example 2:
 * Input: word1 = "abcabc", word2 = "abdcaba"
 * Output: "abdcabcabcaba"
 *
 * Constraints:
 * 1. 1 <= word1.length, word2.length <= 3000
 * 2. word1 and word2 consist only of lowercase English letters.
 */
public class LargestMergeOfTwoStrings {
    public static void main(String[] args) {
        System.out.println(largestMerge("cabaa", "bcaaa"));
        System.out.println(largestMerge("abcabc", "abdcaba"));
    }

    /**
     * 双指针，初识时 i、j 分别指向两个字符串的开始位置，然后开始遍历
     * 如果 a > b，则将 a 加入结果集，i 右移
     * 如果 a < b，则将 b 加入结果集，j 右移
     * 如果 a == b，则判断剩下的字符：
     *    若 remain1 > remain2，则将 a 加入结果集，i 右移；
     *    否则将 b 加入结果集，j 右移
     */
    public static String largestMerge(String word1, String word2) {
        StringBuilder builder = new StringBuilder();
        int i = 0, j = 0;
        while (i < word1.length() && j < word2.length()) {
            char a = word1.charAt(i), b = word2.charAt(j);
            if (a > b) {
                builder.append(a);
                i++;
            } else if (a < b) {
                builder.append(b);
                j++;
            } else {
                if (word1.substring(i).compareTo(word2.substring(j)) > 0) {
                    builder.append(a);
                    i++;
                } else {
                    builder.append(b);
                    j++;
                }
            }
        }
        if (i < word1.length()) {
            builder.append(word1.substring(i));
        }
        if (j < word2.length()) {
            builder.append(word2.substring(j));
        }
        return builder.toString();
    }
}
