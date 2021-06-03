package com.wz.hash;

import java.util.Arrays;

/**
 * You are given an array of strings words and a string chars.
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 * Return the sum of lengths of all good strings in words.
 *
 * Example 1:
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation:
 * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 *
 * Example 2:
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation:
 * The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 *
 * Note:
 * 1. 1 <= words.length <= 1000
 * 2. 1 <= words[i].length, chars.length <= 100
 * 3. All strings contain lowercase English letters only.
 */
public class FindWordsThatCanBeFormedByCharacters {
    public static void main(String[] args) {
        System.out.println(countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach"));
        System.out.println(countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr"));
    }

    /**
     * 使用 count 数组记录每个字符出现次数，然后遍历 words，判断是否能组成每个元素
     */
    public static int countCharacters(String[] words, String chars) {
        int[] count = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            count[chars.charAt(i) - 'a']++;
        }
        int result = 0;
        for (String word : words) {
            if (valid(word, Arrays.copyOf(count, count.length))) {
                result += word.length();
            }
        }
        return result;
    }

    private static boolean valid(String word, int[] count) {
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i) - 'a']--;
            if (count[word.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
