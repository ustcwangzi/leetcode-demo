package com.wz.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * Example 1:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Example 2:
 * Input: s = "a"
 * Output: 1
 *
 * Example 3:
 * Input: s = "bb"
 * Output: 2
 *
 * Constraints:
 * 1. 1 <= s.length <= 2000
 * 2. s consists of lowercase and/or uppercase English letters only.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
        System.out.println(longestPalindrome("bbaaacd"));
    }

    /**
     * 回文串中最多包含一个出现次数为奇数的元素，先使用 map 统计每个字符出现的次数
     * 然后遍历 map，统计出现次数为奇数的元素 oddCount，如果 oddCount <= 1，说明整个字符串可以组成回文串，直接返回字符串长度
     * 如果 oddCount > 1，需要从总长度中减去 (oddCount-1)，因为这些出现次数为奇数的元素中，有一个可以全部使用，
     * 其余元素只能使用其中出现偶数次的部分，假如 s 中包含 aaabbbccc，则 oddCount == 3，aaa 可以全部使用，bbbccc 只能使用 bbcc
     * 也就是从 bbbccc 中每个元素减去一个，最终结果就是需要减去 (oddCount-1)
     */
    public static int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        int oddCount = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount > 1 ? s.length() - oddCount + 1 : s.length();
    }
}
