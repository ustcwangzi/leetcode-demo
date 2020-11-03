package com.wz.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of string words. Return all strings in words which is substring of another word in any order.
 * String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side of words[j].
 *
 * Example 1:
 * Input: words = ["mass","as","hero","superhero"]
 * Output: ["as","hero"]
 * Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
 * ["hero","as"] is also a valid answer.
 *
 * Example 2:
 * Input: words = ["leetcode","et","code"]
 * Output: ["et","code"]
 * Explanation: "et", "code" are substring of "leetcode".
 *
 * Constraints:
 * 1. 1 <= words.length <= 100
 * 2. 1 <= words[i].length <= 30
 * 3. words[i] contains only lowercase English letters.
 * 4. It's guaranteed that words[i] will be unique.
 */
public class StringMatchingInArray {
    public static void main(String[] args) {
        System.out.println(stringMatching(new String[]{"leetcode", "et", "code"}));
    }

    public static List<String> stringMatching(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word1 : words) {
            for (String word2 : words) {
                if (word1.contains(word2) && !word1.equals(word2)) {
                    set.add(word2);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
