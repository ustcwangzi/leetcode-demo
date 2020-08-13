package com.wz.array;

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
 */
public class FindWordsThatCanBeFormedByCharacters {
    public static void main(String[] args) {
        String[] words = new String[]{"cat", "bt", "hat", "tree"};
        String chars = "atach";
        System.out.println(countCharacters(words, chars));

        words = new String[]{"hello", "world", "leetcode"};
        chars = "welldonehoneyr";
        System.out.println(countCharacters(words, chars));
    }

    /**
     * 统计 chars 中每个字符的出现次数，然后遍历 words，将对应次数减1，减过之后的数组如果全大于0，则说明是匹配的
     */
    public static int countCharacters(String[] words, String chars) {
        int[] count = new int[26];
        for (char ch : chars.toCharArray()) {
            count[ch - 'a']++;
        }

        int result = 0;
        for (String word : words) {
            int[] array = Arrays.copyOf(count, count.length);
            for (char ch : word.toCharArray()) {
                array[ch - 'a']--;
            }
            if (valid(array)) {
                result += word.length();
            }
        }

        return result;
    }

    private static boolean valid(int[] array) {
        for (int num : array) {
            if (num < 0) {
                return false;
            }
        }
        return true;
    }
}
