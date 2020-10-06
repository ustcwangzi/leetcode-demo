package com.wz.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * Note:
 * 1. A word is defined as a character sequence consisting of non-space characters only.
 * 2. Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * 3. The input array words contains at least one word.
 *
 * Example 1:
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Example 2:
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 *              because the last line must be left-justified instead of fully-justified.
 *              Note that the second line is also left-justified becase it contains only one word.
 */
public class TextJustification {

    public static void main(String[] args) {
        System.out.println(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int start = 0, end = 0;

        while (start < words.length) {
            int wordsLength = 0;
            while (end < words.length && !(wordsLength + words[end].length() > maxWidth)) {
                wordsLength += words[end].length() + 1;
                end++;
            }
            justify(words, start, end - 1, result, maxWidth);
            start = end;
        }
        return result;
    }


    private static void justify(String[] words, int start, int end, List<String> result, int maxWidth) {
        int wordsLength = 0;
        for (int i = start; i <= end; i++) {
            wordsLength += words[i].length();
        }
        int spaceLength = maxWidth - wordsLength;
        StringBuilder sb = new StringBuilder();
        if (end == words.length - 1 || start == end) {
            sb.append(words[start]);
            for (int i = start + 1; i <= end; i++) {
                sb.append(' ').append(words[i]);
            }

            int rightSpace = spaceLength - (end - start);
            appendSpace(sb, rightSpace);
        } else {
            int basicSpace = spaceLength / (end - start);
            int extraSpace = spaceLength % (end - start);
            for (int i = 0; i < end - start; i++) {
                sb.append(words[start + i]);
                appendSpace(sb, basicSpace);
                if (i < extraSpace) sb.append(' ');
            }
            sb.append(words[end]);
        }
        result.add(sb.toString());
    }

    private static void appendSpace(StringBuilder sb, int num) {
        for (int j = 0; j < num; j++) {
            sb.append(' ');
        }
    }
}
