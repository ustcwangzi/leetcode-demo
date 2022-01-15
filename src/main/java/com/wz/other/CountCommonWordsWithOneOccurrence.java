package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.
 *
 * Example 1:
 * Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 * Output: 2
 * Explanation:
 * - "leetcode" appears exactly once in each of the two arrays. We count this string.
 * - "amazing" appears exactly once in each of the two arrays. We count this string.
 * - "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
 * - "as" appears once in words1, but does not appear in words2. We do not count this string.
 * Thus, there are 2 strings that appear exactly once in each of the two arrays.
 *
 * Example 2:
 * Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 * Output: 0
 * Explanation: There are no strings that appear in each of the two arrays.
 *
 * Example 3:
 * Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 * Output: 1
 * Explanation: The only string that appears exactly once in each of the two arrays is "ab".
 *
 * Constraints:
 * 1. 1 <= words1.length, words2.length <= 1000
 * 2. 1 <= words1[i].length, words2[j].length <= 30
 * 3. words1[i] and words2[j] consists only of lowercase English letters.
 */
public class CountCommonWordsWithOneOccurrence {
    public static void main(String[] args) {
        System.out.println(countWords(new String[]{"leetcode", "is", "amazing", "as", "is"}, new String[]{"amazing", "leetcode", "is"}));
        System.out.println(countWords(new String[]{"a", "ab"}, new String[]{"a", "a", "a", "ab"}));
    }

    /**
     * 使用 map 统计每个单词出现次数，然后判断是否在两个 map 中都出现一次
     */
    public static int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (String word : words1) {
            map1.put(word, map1.getOrDefault(word, 0) + 1);
        }
        for (String word : words2) {
            map2.put(word, map2.getOrDefault(word, 0) + 1);
        }

        int count = 0;
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (entry.getValue() == 1 && map2.getOrDefault(entry.getKey(), 0) == 1) {
                count++;
            }
        }
        return count;
    }
}
