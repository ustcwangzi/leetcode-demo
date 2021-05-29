package com.wz.hash;

import java.util.*;

/**
 * We are given two sentences s1 and s2.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 * Return a list of all uncommon words. You may return the list in any order.
 *
 * Example 1:
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 * Output: ["sweet","sour"]
 *
 * Example 2:
 * Input: s1 = "apple apple", s2 = "banana"
 * Output: ["banana"]
 *
 * Note:
 * 1. 0 <= s1.length <= 200
 * 2. 0 <= s2.length <= 200
 * 3. s1 and s2 both contain only spaces and lowercase letters.
 */
public class UncommonWordsFromTwoSentences {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(uncommonFromSentences("this apple is sweet", "this apple is sour")));
        System.out.println(Arrays.toString(uncommonFromSentences("apple apple", "banana")));
    }

    /**
     * 使用 map 记录每个元素出现次数，然后收集只出现一次的元素
     */
    public static String[] uncommonFromSentences(String s1, String s2) {
        String[] array1 = s1.split(" "), array2 = s2.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String cur : array1) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        for (String cur : array2) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        return result.toArray(new String[0]);
    }
}
