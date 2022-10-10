package com.wz.other;

/**
 * A wonderful string is a string where at most one letter appears an odd number of times.
 * For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
 * Given a string word that consists of the first ten lowercase English letters ('a' through 'j'),
 * return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.
 * A substring is a contiguous sequence of characters in a string.
 *
 * Example 1:
 * Input: word = "aba"
 * Output: 4
 * Explanation: The four wonderful substrings are underlined below:
 * - "aba" -> "a"
 * - "aba" -> "b"
 * - "aba" -> "a"
 * - "aba" -> "aba"
 *
 * Example 2:
 * Input: word = "aabb"
 * Output: 9
 * Explanation: The nine wonderful substrings are underlined below:
 * - "aabb" -> "a"
 * - "aabb" -> "aa"
 * - "aabb" -> "aab"
 * - "aabb" -> "aabb"
 * - "aabb" -> "a"
 * - "aabb" -> "abb"
 * - "aabb" -> "b"
 * - "aabb" -> "bb"
 * - "aabb" -> "b"
 *
 * Example 3:
 * Input: word = "he"
 * Output: 2
 * Explanation: The two wonderful substrings are underlined below:
 * - "he" -> "h"
 * - "he" -> "e"
 *
 * Constraints:
 * 1. 1 <= word.length <= 10^5
 * 2. word consists of lowercase English letters from 'a' to 'j'.
 */
public class NumberOfWonderfulSubstrings {
    public static void main(String[] args) {
        System.out.println(wonderfulSubstrings("aba"));
        System.out.println(wonderfulSubstrings("aabb"));
        System.out.println(wonderfulSubstrings("he"));
    }

    /**
     * 如果字符串 word 的某个子串 word[i..j] 是最美字符串，那么其中最多只有一个字符出现奇数次，这说明：
     * 对于任意一次字符 c 而言，word 的 i-1 前缀 word[0..i−1] 与 j 前缀 word[0..j] 中字符 c 的出现次数必须同奇偶，
     * 同时，最多允许有一个字符 c，它在两个前缀中出现次数的奇偶性不同。
     * 题目保证了 word 中只会包含前 10 个小写字母，因此可以用一个长度为 10 的二进制数 mask 表示 word 的前缀中 [a,j] 出现次数的奇偶性，
     * 其中 mask 的第 i 位为 1 表示第 i 个字母出现了奇数次，0 表示第 i 个字母出现了偶数次。
     * 记 word 的 k 前缀 word[0..k] 对应的二进制数为 mask[k]，则 word[i..j] 是最美字符串，当且仅当 mask[i-1] 和 mask[j] 的二进制表示最多只有一位不同。
     * 对字符串 word 进行遍历，当遍历到 word[i] 时，首先计算出 mask，再遍历 mask 的 10 个二进制位，将其翻转（从 0 变为 1 或者从 1 变为 0）得到 mask'，
     * 此时 mask 和 mask' 的二进制表示恰好有一位不同。
     * 为了快速计算答案，使用一个 count[] 存储每一个 mask 出现的次数，这样直接将答案增加 count[mask'] 即可，
     * 同时，还需要将答案增加 count[mask]，即两个前缀出现字母的奇偶性完全相同。
     */
    public static long wonderfulSubstrings(String word) {
        long result = 0;
        int mask = 0;
        long[] count = new long[1 << 10];
        count[0] = 1;
        for (int i = 0; i < word.length(); i++) {
            mask ^= (1 << (word.charAt(i) - 'a'));
            // 全部字符都出现偶数次
            result += count[mask];
            // 反转，只有一个字符出现奇数次
            for (int j = 0; j < 10; j++) {
                result += count[mask ^ (1 << j)];
            }
            count[mask]++;
        }
        return result;
    }
}
