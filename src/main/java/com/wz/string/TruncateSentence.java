package com.wz.string;

/**
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * Each of the words consists of only uppercase and lowercase English letters (no punctuation).
 * For example, "Hello World", "HELLO", and "hello world hello world" are all sentences.
 * You are given a sentence s and an integer k. You want to truncate s such that it contains only the first k words.
 * Return s after truncating it.
 *
 * Example 1:
 * Input: s = "Hello how are you Contestant", k = 4
 * Output: "Hello how are you"
 * Explanation:
 * The words in s are ["Hello", "how" "are", "you", "Contestant"].
 * The first 4 words are ["Hello", "how", "are", "you"].
 * Hence, you should return "Hello how are you".
 *
 * Example 2:
 * Input: s = "What is the solution to this problem", k = 4
 * Output: "What is the solution"
 * Explanation:
 * The words in s are ["What", "is" "the", "solution", "to", "this", "problem"].
 * The first 4 words are ["What", "is", "the", "solution"].
 * Hence, you should return "What is the solution".
 *
 * Constraints:
 * 1. 1 <= s.length <= 500
 * 2. k is in the range [1, the number of words in s].
 * 3. s consist of only lowercase and uppercase English letters and spaces.
 * 4. The words in s are separated by a single space.
 * 5. There are no leading or trailing spaces.
 */
public class TruncateSentence {
    public static void main(String[] args) {
        System.out.println(truncateSentence("Hello how are you Contestant", 4));
        System.out.println(truncateSentence("What is the solution to this problem", 4));
    }

    /**
     * 前 k 个单词，包含 k-1 个空格，即遇到第 k 个空格时，返回前面的字符串即可
     */
    public static String truncateSentence(String s, int k) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                k--;
            }
            if (k == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }
}
