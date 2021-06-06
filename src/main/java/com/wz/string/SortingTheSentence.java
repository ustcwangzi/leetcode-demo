package com.wz.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * Each word consists of lowercase and uppercase English letters.
 * A sentence can be shuffled by appending the 1-indexed word position to each word then rearranging the words in the sentence.
 * For example, the sentence "This is a sentence" can be shuffled as "sentence4 a3 is2 This1" or "is2 sentence4 This1 a3".
 * Given a shuffled sentence s containing no more than 9 words, reconstruct and return the original sentence.
 *
 * Example 1:
 * Input: s = "is2 sentence4 This1 a3"
 * Output: "This is a sentence"
 * Explanation: Sort the words in s to their original positions "This1 is2 a3 sentence4", then remove the numbers.
 *
 * Example 2:
 * Input: s = "Myself2 Me1 I4 and3"
 * Output: "Me Myself and I"
 * Explanation: Sort the words in s to their original positions "Me1 Myself2 and3 I4", then remove the numbers.
 *
 * Constraints:
 * 1. 2 <= s.length <= 200
 * 2. s consists of lowercase and uppercase English letters, spaces, and digits from 1 to 9.
 * 3. The number of words in s is between 1 and 9.
 * 4. The words in s are separated by a single space.
 * 5. s contains no leading or trailing spaces.
 */
public class SortingTheSentence {
    public static void main(String[] args) {
        System.out.println(sortSentence("is2 sentence4 This1 a3"));
    }

    /**
     * 按照每个元素的最后一位进行排序，排序之后使用 StringBuilder 收集结果
     */
    public static String sortSentence(String s) {
        String[] array = s.split(" ");
        Arrays.sort(array, Comparator.comparingInt(o -> o.charAt(o.length() - 1)));
        StringBuilder builder = new StringBuilder();
        // 收集时需要去除最后一位
        Arrays.stream(array).forEach(c -> builder.append(c, 0, c.length() - 1).append(" "));
        return builder.toString().trim();
    }
}
