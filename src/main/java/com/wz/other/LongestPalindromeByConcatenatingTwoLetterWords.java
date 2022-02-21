package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 * A palindrome is a string that reads the same forward and backward.
 *
 * Example 1:
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 *
 * Example 2:
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 *
 * Example 3:
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 *
 * Constraints:
 * 1. 1 <= words.length <= 10^5
 * 2. words[i].length == 2
 * 3. words[i] consists of lowercase English letters.
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {
    public static void main(String[] args) {
        System.out.println(longestPalindrome(new String[]{"lc", "cl", "gg"}));
        System.out.println(longestPalindrome(new String[]{"cc", "ll", "xx"}));
    }

    /**
     * 使用 map 记录每个 word 出现次数，pairs 记录能组成回文字符串的组数，symmetric 记录对称 word 的个数
     * 遍历数组，若 reverse 存在于 map 中，说明可以组成回文，pairs++，同时从 map 中减少 reverse 出现次数，更新 symmetric
     * 若 reverse 不存在于 map 中，从 map 中增加 word 出现次数，更新 symmetric
     * 最后，每个 word 长度为 2，则每组回文字符串长度为 4，同时 symmetric 可以放在最中间，因此结果为 4*pairs + (symmetric>0 ? 2:0)
     */
    public static int longestPalindrome(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int pairs = 0, symmetric = 0;
        for (String word : words) {
            String reverse = new StringBuilder(word).reverse().toString();
            if (map.getOrDefault(reverse, 0) > 0) {
                pairs++;
                map.put(reverse, map.get(reverse) - 1);
                symmetric -= word.charAt(0) == word.charAt(1) ? 1 : 0;
            } else {
                map.put(word, map.getOrDefault(word, 0) + 1);
                symmetric += word.charAt(0) == word.charAt(1) ? 1 : 0;
            }
        }
        return 4 * pairs + (symmetric > 0 ? 2 : 0);
    }
}
