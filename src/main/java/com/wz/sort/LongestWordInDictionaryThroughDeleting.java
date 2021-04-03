package com.wz.sort;

import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a string array dictionary, return the longest string in the dictionary that
 * can be formed by deleting some of the given string characters. If there is more than one possible result,
 * return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * Output: "apple"
 *
 * Example 2:
 * Input: s = "abpcplea", dictionary = ["a","b","c"]
 * Output: "a"
 *
 * Constraints:
 * 1. 1 <= s.length <= 1000
 * 2. 1 <= dictionary.length <= 1000
 * 3. 1 <= dictionary[i].length <= 1000
 * 4. s and dictionary[i] consist of lowercase English letters.
 */
public class LongestWordInDictionaryThroughDeleting {
    public static void main(String[] args) {
        System.out.println(findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
        System.out.println(findLongestWord("abpcplea", Arrays.asList("a", "b", "c")));
    }

    /**
     * 由于只能删除某些字符，并不能重新排序，所以不能通过统计字符出现次数的方法来判断是否能得到该单词，而是只能按顺序遍历每一个字符
     * 使用 i 记录某个单词的字母位置，遍历给定字符串 s，如果与单词中的某个字母相等，i++，如果没有，就继续往下遍历
     * 这样如果最后 i 和单词长度相等，说明该单词中的所有字母都按顺序出现在了字符串 s 中，更新结果
     */
    public static String findLongestWord(String s, List<String> dictionary) {
        char[] array = s.toCharArray();
        String result = "";
        for (String word : dictionary) {
            // 无法组成 或 长度较大
            if (!valid(array, word) || word.length() < result.length()) {
                continue;
            }
            if (word.length() > result.length() || word.compareTo(result) < 0) {
                result = word;
            }
        }

        return result;
    }

    private static boolean valid(char[] array, String word) {
        int i = 0;
        for (char c : array) {
            if (i < word.length() && c == word.charAt(i)) {
                i++;
            }
        }
        return i == word.length();
    }
}
