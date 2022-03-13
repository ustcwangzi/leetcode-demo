package com.wz.string;

/**
 * You are given two strings s and t. In one step, you can append any character to either s or t.
 * Return the minimum number of steps to make s and t anagrams of each other.
 * An anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 *
 * Example 1:
 * Input: s = "leetcode", t = "coats"
 * Output: 7
 * Explanation:
 * - In 2 steps, we can append the letters in "as" onto s = "leetcode", forming s = "leetcodeas".
 * - In 5 steps, we can append the letters in "leede" onto t = "coats", forming t = "coatsleede".
 * "leetcodeas" and "coatsleede" are now anagrams of each other.
 * We used a total of 2 + 5 = 7 steps.
 * It can be shown that there is no way to make them anagrams of each other with less than 7 steps.
 *
 * Example 2:
 * Input: s = "night", t = "thing"
 * Output: 0
 * Explanation: The given strings are already anagrams of each other. Thus, we do not need any further steps.
 *
 * Constraints:
 * 1. 1 <= s.length, t.length <= 2 * 10^5
 * 2. s and t consist of lowercase English letters.
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagramII {
    public static void main(String[] args) {
        System.out.println(minSteps("leetcode", "coats"));
    }

    /**
     * 与 {@link MinimumNumberOfStepsToMakeTwoStringsAnagram} 类似
     * 先统计 s 中每个字符出现次数，累加到 count 中，再统计 t 中每个字符出现次数，将 count 中对应次数减一
     * 那么 count 中不为 0 的次数就是缺少的字符个数，累加即可
     */
    public static int minSteps(String s, String t) {
        int[] count = new int[26];
        for (char cur : s.toCharArray()) {
            count[cur - 'a']++;
        }
        for (char cur : t.toCharArray()) {
            count[cur - 'a']--;
        }
        int step = 0;
        for (int num : count) {
            step += Math.abs(num);
        }
        return step;
    }
}
