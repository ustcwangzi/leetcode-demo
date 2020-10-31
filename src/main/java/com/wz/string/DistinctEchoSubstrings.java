package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself (i.e. it can be written as a + a where a is some string).
 *
 *
 *
 * Example 1:
 *
 * Input: text = "abcabcabc"
 * Output: 3
 * Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
 * Example 2:
 *
 * Input: text = "leetcodeleetcode"
 * Output: 2
 * Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 2000
 * text has only lowercase English letters.
 */
public class DistinctEchoSubstrings {
    public static void main(String[] args) {
        System.out.println(distinctEchoSubstrings("leetcodeleetcode"));
    }

    /**
     * 暴力求解
     */
    public static int distinctEchoSubstrings(String text) {
        Set<String> result = new HashSet<>();
        for (int i = 2; i <= text.length(); i = i + 2) {
            for (int j = 0; j < text.length() - i + 1; j++) {
                String str = text.substring(j, j + i);
                if (str.substring(0, str.length() / 2).equals(str.substring(str.length() / 2))) {
                    result.add(str);
                }
            }
        }
        return result.size();
    }
}
