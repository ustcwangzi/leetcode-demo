package com.wz.string;

/**
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * 1. All letters in this word are capitals, like "USA".
 * 2. All letters in this word are not capitals, like "leetcode".
 * 3. Only the first letter in this word is capital, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 *
 * Example 1:
 * Input: "USA"
 * Output: True
 *
 * Example 2:
 * Input: "FlaG"
 * Output: False
 *
 * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 */
public class DetectCapital {
    public static void main(String[] args) {
        System.out.println(detectCapitalUse("USA"));
        System.out.println(detectCapitalUse("FlaG"));
    }

    public static boolean detectCapitalUse(String word) {
        int capCount = 0, lowerCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                capCount++;
            } else {
                lowerCount++;
            }
        }

        return (capCount == 1 && Character.isUpperCase(word.charAt(0))) ||
                capCount == word.length() || lowerCount == word.length();
    }
}
