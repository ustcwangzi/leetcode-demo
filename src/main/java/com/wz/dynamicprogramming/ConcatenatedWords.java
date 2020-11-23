package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Note:
 * 1. The number of elements of the given array will not exceed 10,000
 * 2. The length sum of elements in the given array will not exceed 600,000.
 * 3. All the input string will only include lower case letters.
 * 4. The returned elements order does not matter.
 */
public class ConcatenatedWords {
    public static void main(String[] args) {
        System.out.println(findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}));
    }

    /**
     * 对每个单词调用 {@link WordBreak}
     */
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new LinkedList<>();
        if (words.length <= 2) {
            return result;
        }
        Set<String> set = Arrays.stream(words).collect(Collectors.toSet());
        for (String word : words) {
            set.remove(word);
            if (word.length() == 0) {
                continue;
            }
            if (wordBreak(word, set)) {
                result.add(word);
            }
            set.add(word);
        }
        return result;
    }

    /**
     * dp[i] 表示 s[0, i-1] 是否匹配
     * 如果某个前缀可以匹配，如: leet 可以被词典匹配，那么就可以判断 code 是否出现在词典中，这样就可以判断 leetcode 是否可以匹配了
     * 因此，dp[i] = dp[j] && exist(str(j,i)) for j < i
     */
    private static boolean wordBreak(String s, Set<String> wordDict) {
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
