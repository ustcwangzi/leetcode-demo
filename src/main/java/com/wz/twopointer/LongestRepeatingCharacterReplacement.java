package com.wz.twopointer;

/**
 * You are given a string s and an integer k. You can choose any character of the string and
 * change it to any other uppercase English character. You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of only uppercase English letters.
 * 3. 0 <= k <= s.length
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));
        System.out.println(characterReplacement("AABABBA", 1));
    }

    /**
     * 双指针
     * 使用 count[] 记录 s[left,right] 元素频次，使用 max 记录 s[left,right] 元素频次最大值
     * 如果当前长度 right-left+1 > max+k，说明需要替换的元素个数超过 k 个，将 s[left] 从 count[] 中移除，同时 left 右移
     * 每遍历一个 right，就更新结果
     */
    public static int characterReplacement(String s, int k) {
        int left = 0, right = 0, result = 0, max = 0;
        int[] count = new int[26];
        while (right < s.length()) {
            max = Math.max(max, ++count[s.charAt(right) - 'A']);
            if (right - left + 1 > max + k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            // 注意，right 不能保持不变，不然会导致 count 重复计算
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }
}
