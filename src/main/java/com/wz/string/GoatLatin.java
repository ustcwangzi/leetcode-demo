package com.wz.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 * The rules of Goat Latin are as follows:
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 *
 * Example 1:
 * Input: "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 */
public class GoatLatin {
    public static void main(String[] args) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
    }

    /**
     * 如果单词是元音开头的，直接在但此后加ma
     * 如果是非元音开头的单词，把首字母移到末尾，并且加ma，同时根据当前是第几个单词，后面加几个a
     */
    public static String toGoatLatin(String S) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        StringBuilder result = new StringBuilder();
        StringBuilder suf = new StringBuilder("a");
        for (String word : S.split(" ")) {
            if (result.length() != 0) {
                result.append(" ");
            }
            if (set.contains(word.charAt(0))) {
                result.append(word);
            } else {
                result.append(word.substring(1));
                result.append(word.charAt(0));
            }
            result.append("ma").append(suf);
            suf.append("a");
        }
        return result.toString();
    }
}
