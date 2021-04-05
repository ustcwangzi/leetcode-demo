package com.wz.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 * 1. First letter is in upper case.
 * 2. Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
 * If two words have the same length, arrange them in their original order.
 * Return the new text following the format shown above.
 *
 * Example 1:
 * Input: text = "Leetcode is cool"
 * Output: "Is cool leetcode"
 * Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
 * Output is ordered by length and the new first word starts with capital letter.
 *
 * Example 2:
 * Input: text = "Keep calm and code on"
 * Output: "On and keep calm code"
 * Explanation: Output is ordered as follows:
 * "On" 2 letters.
 * "and" 3 letters.
 * "keep" 4 letters in case of tie order by position in original text.
 * "calm" 4 letters.
 * "code" 4 letters.
 *
 * Constraints:
 * 1. text begins with a capital letter and then contains lowercase letters and single space between words.
 * 2. 1 <= text.length <= 10^5
 */
public class RearrangeWordsInSentence {
    public static void main(String[] args) {
        System.out.println(arrangeWords("Leetcode is cool"));
    }

    /**
     * 将 text 转为小写后，用" "将每个单词分开，然后按照长度进行排序，最后将第一个字符改为大写
     */
    public static String arrangeWords(String text) {
        String[] array = text.toLowerCase().split(" ");
        Arrays.sort(array, Comparator.comparingInt(String::length));
        String result = String.join(" ", array);
        return Character.toUpperCase(result.charAt(0)) + result.substring(1);
    }
}
