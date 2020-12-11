package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of words, each word consists of English lowercase letters.
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
 * For example, "abc" is a predecessor of "abac".
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2,
 * word_2 is a predecessor of word_3, and so on.
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 * Example 1:
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chain is "a","ba","bda","bdca".
 *
 * Example 2:
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 *
 * Constraints:
 * 1. 1 <= words.length <= 1000
 * 2. 1 <= words[i].length <= 16
 * 3. words[i] only consists of English lowercase letters.
 */
public class LongestStringChain {
    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
    }

    /**
     * dp[i] 表示为以 words[i] 作为结尾时的最长长度，
     * 遍历 0～i，对于每个 j，如果 words[j] 能够作为words[i]的predecessor，dp[i] = Math.max(dp[j]+1, dp[i])
     */
    public static int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int result = Integer.MIN_VALUE, n = words.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    private static boolean isPredecessor(String str1, String str2) {
        if (str1.length() + 1 != str2.length()) {
            return false;
        }
        for (int i = 0, j = 0, diff = 0; i < str1.length(); ) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                diff++;
                if (diff > 1) {
                    return false;
                }
                j++;
            }
        }
        return true;
    }
}
