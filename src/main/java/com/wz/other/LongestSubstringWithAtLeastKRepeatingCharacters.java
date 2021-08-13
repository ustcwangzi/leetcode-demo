package com.wz.other;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 *
 * Example 1:
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 *
 * Example 2:
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^4
 * 2. s consists of only lowercase English letters.
 * 3. 1 <= k <= 10^5
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(longestSubstring("ababbc", 2));
    }

    /**
     * 求一个最大子字符串并且每个字符必须至少出现 k 次
     * 分治，遍历 s，并记录每个字符出现次数，如果所有字符出现次数都不小于 k，直接返回 s 的长度即可
     * 如果字符 c 的出现次数小于 k，因为满足条件的 substring 永远不会包含c，所以结果一定是在以 c 为分割下的某个 substring 中
     * 因此可以将 c 当做分割点，去除 c，分割成两份，然后再分别递归求解左边和右边，两者取其大就是最终结果
     */
    public static int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        int index = 0;
        while (index < s.length() && count[s.charAt(index) - 'a'] >= k) {
            index++;
        }
        if (index == s.length()) {
            return s.length();
        }

        int left = longestSubstring(s.substring(0, index), k);
        int right = longestSubstring(s.substring(index + 1), k);
        return Math.max(left, right);
    }
}
