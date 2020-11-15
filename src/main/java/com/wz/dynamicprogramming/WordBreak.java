package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note:
 * 1. The same word in the dictionary may be reused multiple times in the segmentation.
 * 2. You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {
    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
    }

    /**
     * dp[i] 表示 s[0, i-1] 是否匹配
     * 如果某个前缀可以匹配，如: leet 可以被词典匹配，那么就可以判断 code 是否出现在词典中，这样就可以判断 leetcode 是否可以匹配了
     * 因此，dp[i] = dp[j] && exist(str(j,i)) for j < i
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
