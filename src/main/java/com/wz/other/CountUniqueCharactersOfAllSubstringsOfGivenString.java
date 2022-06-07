package com.wz.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
 * For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 *
 * Example 1:
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Every substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 *
 * Example 2:
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 *
 * Example 3:
 * Input: s = "LEETCODE"
 * Output: 92
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of uppercase English letters only.
 */
public class CountUniqueCharactersOfAllSubstringsOfGivenString {
    public static void main(String[] args) {
        System.out.println(uniqueLetterString("ABC"));
        System.out.println(uniqueLetterString("ABA"));
        System.out.println(uniqueLetterString("LEETCODE"));
    }

    /**
     * 对每一个 i 向前找到第一个 s[i] == s[j]，向后找到第一个 s[i] == s[k]
     * 则 i 的贡献度为 (i - j) * (k - i)，进行累加即可
     */
    public static int uniqueLetterString(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            map.putIfAbsent(cur, new ArrayList<>());
            map.get(cur).add(i);
        }

        int result = 0;
        for (List<Integer> list : map.values()) {
            for (int i = 0; i < list.size(); i++) {
                int left, right;
                if (i == 0) {
                    left = list.get(i) + 1;
                } else {
                    left = list.get(i) - list.get(i - 1);
                }

                if (i == list.size() - 1) {
                    right = s.length() - list.get(i);
                } else {
                    right = list.get(i + 1) - list.get(i);
                }

                result += left * right;
            }
        }
        return result;
    }
}
