package com.wz.string;

/**
 * A string is considered beautiful if it satisfies the following conditions:
 * 1. Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
 * 2. The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
 * For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.
 * Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 * Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
 * Output: 13
 * Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of length 13.
 *
 * Example 2:
 * Input: word = "aeeeiiiioooauuuaeiou"
 * Output: 5
 * Explanation: The longest beautiful substring in word is "aeiou" of length 5.
 *
 * Constraints:
 * 1. 1 <= word.length <= 5 * 10^5
 * 2. word consists of characters 'a', 'e', 'i', 'o', and 'u'.
 */
public class LongestSubstringOfAllVowelsInOrder {
    public static void main(String[] args) {
        System.out.println(longestBeautifulSubstring("aeiaaioaaaaeiiiiouuuooaauuaeiu"));
        System.out.println(longestBeautifulSubstring("aeeeiiiioooauuuaeiou"));
    }

    /**
     * 遍历字符串，记录当前长度 currentLength 和 满足条件字符个数 count
     * 如果当前字符小于前一个字符，说明出现了逆序，将 currentLength 和 count 置为 0，然后 currentLength++，代表从当前开始重新计算
     * 如果当前字符不等于前一个字符，说明出现了新的元音字符，count++，当 count 达到 5 时，更新结果
     */
    public static int longestBeautifulSubstring(String word) {
        int result = 0, currentLength = 0, count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (i > 0 && word.charAt(i) < word.charAt(i - 1)) {
                count = 0;
                currentLength = 0;
            }
            currentLength++;
            if (i == 0 || word.charAt(i) != word.charAt(i - 1)) {
                count++;
            }
            if (count == 5) {
                result = Math.max(result, currentLength);
            }
        }
        return result;
    }
}
