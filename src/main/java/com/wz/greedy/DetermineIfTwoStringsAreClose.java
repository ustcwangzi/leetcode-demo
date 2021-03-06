package com.wz.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Two strings are considered close if you can attain one from the other using the following operations:
 * 1. Operation 1: Swap any two existing characters. For example, abcde -> aecdb
 * 2. Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 *    For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 *
 * Example 1:
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 *
 * Example 2:
 * Input: word1 = "cabbba", word2 = "aabbss"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any amount of operations.
 *
 * Constraints:
 * 1. 1 <= word1.length, word2.length <= 10^5
 * 2. word1 and word2 contain only lowercase English letters.
 */
public class DetermineIfTwoStringsAreClose {
    public static void main(String[] args) {
        System.out.println(closeStrings("cabbba", "abbccc"));
        System.out.println(closeStrings("cabbba", "aabbss"));
    }

    /**
     * 所包含的字母种类必须相同，整体上的频率也相同
     * 使用数组统计字母出现的频次，使用 set 统计字母出现的种类
     */
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] count1 = new int[26], count2 = new int[26];
        Set<Character> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (char cur : word1.toCharArray()) {
            count1[cur - 'a']++;
            set1.add(cur);
        }
        for (char cur : word2.toCharArray()) {
            count2[cur - 'a']++;
            set2.add(cur);
        }

        Arrays.parallelSort(count1);
        Arrays.parallelSort(count2);
        return Arrays.equals(count1, count2) && set1.equals(set2);
    }
}
