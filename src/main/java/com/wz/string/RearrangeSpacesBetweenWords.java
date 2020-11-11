package com.wz.string;

/**
 * You are given a string text of words that are placed among some number of spaces.
 * Each word consists of one or more lowercase English letters and are separated by at least one space.
 * It's guaranteed that text contains at least one word.
 * Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that
 * number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end,
 * meaning the returned string should be the same length as text.
 * Return the string after rearranging the spaces.
 *
 * Example 1:
 * Input: text = "  this   is  a sentence "
 * Output: "this   is   a   sentence"
 * Explanation: There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
 *
 * Example 2:
 * Input: text = " practice   makes   perfect"
 * Output: "practice   makes   perfect "
 * Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space.
 *              We place this extra space at the end of the string.
 *
 * Example 3:
 * Input: text = "hello   world"
 * Output: "hello   world"
 *
 * Constraints:
 * 1. 1 <= text.length <= 100
 * 2. text consists of lowercase English letters and ' '.
 * 3. text contains at least one word.
 */
public class RearrangeSpacesBetweenWords {
    public static void main(String[] args) {
        System.out.println(reorderSpaces(" practice   makes   perfect"));
    }

    public static String reorderSpaces(String text) {
        String[] words = text.trim().split("\\s+");
        int spaceCount = 0, wordLength = words.length;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        if (spaceCount == 0) {
            return text;
        }

        int add = wordLength == 1 ? 0 : spaceCount / (wordLength - 1);
        int res = wordLength == 1 ? spaceCount : spaceCount % (wordLength - 1);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(words[i]);
            int len = i == words.length - 1 ? res : add;
            for (int j = 0; j < len; j++) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
