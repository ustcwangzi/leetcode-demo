package com.wz.dynamicprogramming;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * Note:
 * 1. The same word in the dictionary may be reused multiple times in the segmentation.
 * 2. You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreakII {
    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }

    /**
     * DFS
     * 从字典表中开始拿字符串与 s 的头部进行匹配，若匹配成功，则继续拿字典表与 s 的剩余字符串进行匹配，直至 s 剩余字符串为空
     * 用 map 保存每个字符串的不同切分结果，用以减少重复匹配，最后返回 map 中 s 的切分结果即可
     */
    public static List<String> wordBreak(String s, List<String> wordDict) {
        return dsf(s, wordDict, new HashMap<>());
    }

    private static List<String> dsf(String s, List<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> result = new LinkedList<>();
        for (String word : wordDict) {
            if (!s.startsWith(word)) {
                continue;
            }

            // word 已匹配，移除 word 继续向后匹配
            String next = s.substring(word.length());
            if (next.length() == 0) {
                result.add(word);
            } else {
                List<String> subList = dsf(next, wordDict, map);
                for (String sub : subList) {
                    result.add(word + " " + sub);
                }
            }
        }

        map.put(s, result);
        return result;
    }
}
