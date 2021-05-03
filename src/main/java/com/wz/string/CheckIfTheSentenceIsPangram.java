package com.wz.string;

import java.util.Arrays;

/**
 * A pangram is a sentence where every letter of the English alphabet appears at least once.
 * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
 *
 * Example 1:
 * Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
 * Output: true
 * Explanation: sentence contains at least one of every letter of the English alphabet.
 *
 * Example 2:
 * Input: sentence = "leetcode"
 * Output: false
 *
 * Constraints:
 * 1. 1 <= sentence.length <= 1000
 * 2. sentence consists of lowercase English letters.
 */
public class CheckIfTheSentenceIsPangram {
    public static void main(String[] args) {
        System.out.println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        System.out.println(checkIfPangram("leetcode"));
    }

    /**
     * 用数组统计每个字符的出现次数，最后判断是否有没出现过的字符
     */
    public static boolean checkIfPangram(String sentence) {
        if (sentence.length() < 26) {
            return false;
        }
        int[] array = new int[26];
        for (int i = 0; i < sentence.length(); i++) {
            array[sentence.charAt(i) - 'a']++;
        }
        return Arrays.stream(array).filter(value -> value == 0).count() == 0;
    }
}
