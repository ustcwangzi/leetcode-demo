package com.wz.string;

/**
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
 * Return the minimum number of steps to make t an anagram of s.
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 *
 * Example 1:
 * Input: s = "bab", t = "aba"
 * Output: 1
 * Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 *
 * Example 2:
 * Input: s = "leetcode", t = "practice"
 * Output: 5
 * Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 *
 * Example 3:
 * Input: s = "anagram", t = "mangaar"
 * Output: 0
 * Explanation: "anagram" and "mangaar" are anagrams.
 *
 * Example 4:
 * Input: s = "xxyyzz", t = "xxyyzz"
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= s.length <= 50000
 * 2. s.length == t.length
 * 3. s and t contain lower-case English letters only.
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagram {
    public static void main(String[] args) {
        System.out.println(minSteps("leetcode", "practice"));
        System.out.println(minSteps("xxyyzz", "xxyyzz"));
    }

    /**
     * 先统计 t 中的每个字符出现次数 count，然后假设 s 变换到 t 需要全部替换，即 n 步
     * 遍历 s，对于 s 中的每个字符，若 count[s[i]] > 0，说明该字符不用替换，对应出现次数减1，同时步骤减1
     */
    public static int minSteps(String s, String t) {
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            count[t.charAt(i) - 'a']++;
        }

        int result = n;
        for (int i = 0; i < n; i++) {
            int cur = s.charAt(i) - 'a';
            if (count[cur] > 0) {
                count[cur]--;
                result--;
            }
        }
        return result;
    }
}
