package com.wz.other;

/**
 * You are given an array of strings words (0-indexed).
 * In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any character from words[i] to any position in words[j].
 * Return true if you can make every string in words equal using any number of operations, and false otherwise.
 *
 * Example 1:
 * Input: words = ["abc","aabc","bc"]
 * Output: true
 * Explanation: Move the first 'a' in words[1] to the front of words[2],
 * to make words[1] = "abc" and words[2] = "abc".
 * All the strings are now equal to "abc", so return true.
 *
 * Example 2:
 * Input: words = ["ab","a"]
 * Output: false
 * Explanation: It is impossible to make all the strings equal using the operation.
 *
 * Constraints:
 * 1. 1 <= words.length <= 100
 * 2. 1 <= words[i].length <= 100
 * 3. words[i] consists of lowercase English letters.
 */
public class RedistributeCharactersToMakeAllStringsEqual {
    public static void main(String[] args) {
        System.out.println(makeEqual(new String[]{"abc", "aabc", "bc"}));
        System.out.println(makeEqual(new String[]{"ab", "a"}));
    }

    /**
     * 统计每次字符出现的次数，然后判断是不是每个字符都能够平分，即出现次数能否被 words.length 整除
     */
    public static boolean makeEqual(String[] words) {
        int[] count = new int[26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i) - 'a']++;
            }
        }
        for (int cur : count) {
            if (cur % words.length != 0) {
                return false;
            }
        }
        return true;
    }
}
