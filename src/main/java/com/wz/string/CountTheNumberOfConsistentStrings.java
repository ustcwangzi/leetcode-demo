package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string allowed consisting of distinct characters and an array of strings words.
 * A string is consistent if all characters in the string appear in the string allowed.
 * Return the number of consistent strings in the array words.
 *
 * Example 1:
 * Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * Output: 2
 * Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
 *
 * Example 2:
 * Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * Output: 7
 * Explanation: All strings are consistent.
 *
 * Constraints:
 * 1. 1 <= words.length <= 10^4
 * 2. 1 <= allowed.length <= 26
 * 3. 1 <= words[i].length <= 10
 * 4. The characters in allowed are distinct.
 * 5. words[i] and allowed contain only lowercase English letters.
 */
public class CountTheNumberOfConsistentStrings {
    public static void main(String[] args) {
        System.out.println(countConsistentStrings("ab", new String[]{"ad", "bd", "aaab", "baa", "badab"}));
    }

    /**
     * 将 allowed 的字符存入 set 中，然后遍历 words，若当前 word 的字符全在 set 中则结果加一
     */
    public static int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>(allowed.length());
        for (int i = 0; i < allowed.length(); i++) {
            set.add(allowed.charAt(i));
        }

        int count = 0;
        for (String word : words) {
            boolean consistent = true;
            for (int i = 0; i < word.length(); i++) {
                if (!set.contains(word.charAt(i))) {
                    consistent = false;
                    break;
                }
            }
            count += consistent ? 1 : 0;
        }
        return count;
    }
}
