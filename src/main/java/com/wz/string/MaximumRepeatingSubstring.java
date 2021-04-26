package com.wz.string;

/**
 * For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence.
 * The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence.
 * If word is not a substring of sequence, word's maximum k-repeating value is 0.
 * Given strings sequence and word, return the maximum k-repeating value of word in sequence.
 *
 * Example 1:
 * Input: sequence = "ababc", word = "ab"
 * Output: 2
 * Explanation: "abab" is a substring in "ababc".
 *
 * Example 2:
 * Input: sequence = "ababc", word = "ba"
 * Output: 1
 * Explanation: "ba" is a substring in "ababc". "baba" is not a substring in "ababc".
 *
 * Example 3:
 * Input: sequence = "ababc", word = "ac"
 * Output: 0
 * Explanation: "ac" is not a substring in "ababc".
 *
 * Constraints:
 * 1. 1 <= sequence.length <= 100
 * 2. 1 <= word.length <= 100
 * 3. sequence and word contains only lowercase English letters.
 */
public class MaximumRepeatingSubstring {
    public static void main(String[] args) {
        System.out.println(maxRepeating("ababc", "ab"));
        System.out.println(maxRepeating("abcabc", "ab"));
        System.out.println(maxRepeating("ababc", "ba"));
        System.out.println(maxRepeating("ababc", "ac"));
    }

    /**
     * 使用 StringBuilder 记录当前需要比对的字符串，然后判断 sequence 中是否包含 builder
     */
    public static int maxRepeating(String sequence, String word) {
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while (sequence.contains(builder.append(word).toString())) {
            count++;
        }
        return count;
    }
}
