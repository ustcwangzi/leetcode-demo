package com.wz.other;

/**
 * There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.
 * Given a string text of words separated by a single space (no leading or trailing spaces) and a string brokenLetters
 * of all distinct letter keys that are broken, return the number of words in text you can fully type using this keyboard.
 *
 * Example 1:
 * Input: text = "hello world", brokenLetters = "ad"
 * Output: 1
 * Explanation: We cannot type "world" because the 'd' key is broken.
 *
 * Example 2:
 * Input: text = "leet code", brokenLetters = "lt"
 * Output: 1
 * Explanation: We cannot type "leet" because the 'l' and 't' keys are broken.
 *
 * Example 3:
 * Input: text = "leet code", brokenLetters = "e"
 * Output: 0
 * Explanation: We cannot type either word because the 'e' key is broken.
 *
 * Constraints:
 * 1. 1 <= text.length <= 10^4
 * 2. 0 <= brokenLetters.length <= 26
 * 3. text consists of words separated by a single space without any leading or trailing spaces.
 * 4. Each word only consists of lowercase English letters.
 * 5. brokenLetters consists of distinct lowercase English letters.
 */
public class MaximumNumberOfWordsYouCanType {
    public static void main(String[] args) {
        System.out.println(canBeTypedWords("leet code", "lt"));
    }

    /**
     * 使用 broken[] 数组记录坏掉的字母，将 text 分割为单词，然后遍历每个单词，判断其中包含的字母是否在 broken[] 中
     */
    public static int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for (int i = 0; i < brokenLetters.length(); i++) {
            broken[brokenLetters.charAt(i) - 'a'] = true;
        }

        int result = 0;
        String[] array = text.split(" ");
        for (String word : array) {
            result++;
            for (int i = 0; i < word.length(); i++) {
                if (broken[word.charAt(i) - 'a']) {
                    result--;
                    break;
                }
            }
        }
        return result;
    }
}
