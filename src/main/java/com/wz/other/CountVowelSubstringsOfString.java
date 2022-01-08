package com.wz.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A substring is a contiguous (non-empty) sequence of characters within a string.
 * A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.
 * Given a string word, return the number of vowel substrings in word.
 *
 * Example 1:
 * Input: word = "aeiouu"
 * Output: 2
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "aeiouu"
 * - "aeiouu"
 *
 * Example 2:
 * Input: word = "unicornarihan"
 * Output: 0
 * Explanation: Not all 5 vowels are present, so there are no vowel substrings.
 *
 * Example 3:
 * Input: word = "cuaieuouac"
 * Output: 7
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 *
 * Constraints:
 * 1. 1 <= word.length <= 100
 * 2. word consists of lowercase English letters only.
 */
public class CountVowelSubstringsOfString {
    public static void main(String[] args) {
        System.out.println(countVowelSubstrings("aeiouu"));
        System.out.println(countVowelSubstrings("unicornarihan"));
    }

    /**
     * 只包含五个元音字符的子串个数，滑动窗口 [i...j]，对于每个 i，j 从 i 开始向右移动，判断每个字符是否满足要求
     * 若当前字符 word[j] 不是元音字符，直接结束本次循环，否则将其加入到 set 中统计是否满足五个元音字符的要求
     */
    public static int countVowelSubstrings(String word) {
        int result = 0;
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < word.length() - 4; i++) {
            Set<Character> set = new HashSet<>(5);
            for (int j = i; j < word.length(); j++) {
                if (!vowelSet.contains(word.charAt(j))) {
                    break;
                }
                set.add(word.charAt(j));
                if (set.size() == 5) {
                    result++;
                }
            }
        }
        return result;
    }
}
