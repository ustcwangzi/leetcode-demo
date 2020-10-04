package com.wz.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string s and an array of strings words of the same length.
 * Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once,
 * in any order, and without any intervening characters.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 *
 * Example 2:
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 */
public class SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
    }

    /**
     * 给定一个字符串S和一个字符串数组L，L中的字符串长度都相等，找出S中所有的子串恰好包含L中所有字符各一次，返回子串的起始位置
     *
     * 在每个子字符串窗口中，搜索给定字符串中每个单词的长度，每个子字符串窗口的长度=来自words []的串联单词的长度之和
     * 只要在子字符串窗口中找到所有单词，便会存储该子字符串窗口的起始索引。
     * 将问题的解决分为四个步骤：
     * 1. wordsMap 存储 words[] 的每个单词的个数
     * 2. 计算串联词的总长度 concatWordLen
     * 3. 遍历字符串，并从字符串中提取单词，将提取的单词出现次数存储在 subWordsMap
     *    如果找到的单词不在原始单词 wordsMap 中，或者 subWordsMap 的出现次数大于 wordsMap 中该单词的出现次数，则跳出该窗口
     * 4. 一旦子字符串中的所有单词都匹配到长度 concatWordLen，检查 subWordsMap 与 wordsMap，如果内容匹配，将添加到结果中
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        int wordLen = words[0].length();
        int concatWordLen = wordLen * words.length;

        for (int i = 0; i <= s.length() - concatWordLen; i++) {
            int startingWordIdx = i;
            Map<String, Integer> subWordsMap = new HashMap<>();
            while (startingWordIdx < i + concatWordLen) {
                String word = s.substring(startingWordIdx, startingWordIdx + wordLen);
                if (!wordsMap.containsKey(word) || (wordsMap.containsKey(word) && subWordsMap.containsKey(word) &&
                        subWordsMap.get(word) > wordsMap.get(word))) {
                    break;
                }
                subWordsMap.put(word, subWordsMap.getOrDefault(word, 0) + 1);
                startingWordIdx = startingWordIdx + wordLen;
            }

            if (wordsMap.equals(subWordsMap)) {
                result.add(i);
            }
        }

        return result;
    }
}
