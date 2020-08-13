package com.wz.array;

import java.util.Arrays;

/**
 * Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s.
 * For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
 * Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words
 * such that f(queries[i]) < f(W), where W is a word in words.
 *
 * Example 1:
 * Input: queries = ["cbd"], words = ["zaaaz"]
 * Output: [1]
 * Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
 *
 * Example 2:
 * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * Output: [1,2]
 * Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 *
 * Constraints:
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] are English lowercase letters.
 */
public class CompareStringsByFrequencyOfSmallestCharacter {
    public static void main(String[] args) {
        String[] queries = new String[]{"cbd"}, words = new String[]{"zaaaz"};
        System.out.println(Arrays.toString(numSmallerByFrequency(queries, words)));

        queries = new String[]{"bbb", "cc"};
        words = new String[]{"a", "aa", "aaa", "aaaa"};
        System.out.println(Arrays.toString(numSmallerByFrequency(queries, words)));
    }

    /**
     * 求 queries[i] 中的每个字符串，words 中有多少个字符串的频率比它小
     * 直接双层循环即可
     */
    public static int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] wordFreq = new int[words.length];
        for (int j = 0; j < words.length; j++) {
            wordFreq[j] = minFrequency(words[j]);
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int queryFreq = minFrequency(queries[i]);
            int count = 0;
            for (int j = 0; j < words.length; j++) {
                if (queryFreq < wordFreq[j]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    private static int minFrequency(String str) {
        int[] frequency = new int[26];
        for (char ch : str.toCharArray()) {
            frequency[ch - 'a']++;
        }
        for (int count : frequency) {
            if (count > 0) {
                return count;
            }
        }
        return 0;
    }
}
