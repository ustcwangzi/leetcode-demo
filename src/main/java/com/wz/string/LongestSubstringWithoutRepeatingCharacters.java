package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abba"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    /**
     * 动态规划
     * dp[i] 代表以 s[i] 为结尾的最长无重复子串长度，并使用 set 保存当前子串中包含的字符，对于每个 s[i]
     * 若 set 中不包含 s[i]，则 dp[i] = dp[i-1] + 1
     * 否则，清除 set，并从 i-1 开始向前遍历直到找到 s[j] == s[i]，此时 dp[i] = i - j
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }

        char[] array = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int[] dp = new int[array.length];
        dp[0] = 1;
        set.add(array[0]);

        int result = 1;
        for (int i = 1; i < array.length; i++) {
            if (set.contains(array[i])) {
                set.clear();
                for (int j = i - 1; j >= 0; j--) {
                    set.add(array[j]);
                    if (array[j] == array[i]) {
                        dp[i] = i - j;
                        break;
                    }
                }
            } else {
                set.add(array[i]);
                dp[i] = dp[i - 1] + 1;
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
