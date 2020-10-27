package com.wz.string;

/**
 * Given a string text, we are allowed to swap two of the characters in the string.
 * Find the length of the longest substring with repeated characters.
 *
 * Example 1:
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'.
 * Then, the longest repeated character substring is "aaa", which its length is 3.
 *
 * Example 2:
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
 *
 * Example 3:
 * Input: text = "abcdef"
 * Output: 1
 *
 * Constraints:
 * 1. 1 <= text.length <= 20000
 * 2. text consist of lowercase English characters only.
 */
public class SwapForLongestRepeatedCharacterSubstring {
    public static void main(String[] args) {
        System.out.println(maxRepOpt1("aaabaaa"));
        System.out.println(maxRepOpt1("abcdef"));
    }

    /**
     * 动态规划。
     * f(i) 表示没有交换过以位置 i 结尾的字符时最长重复字符个数，g(i) 表示最多交换过一个以位置 i 结尾的字符时最长重复字符个数
     * 指定需要交换的字符 cur，遍历 text
     * 若当前字符等于指定字符 cur， f(i) = f(i-1) + 1，g(i) = g(i-1) + 1
     * 若当前字符不等于指定字符 cur， g(i) = f(i-1) + 1，f(i) = 0
     * 可以看到转移方程只依赖了前一个值，可以运用状态压缩将两个数组压缩成两个变量
     * 同时需要注意可能没有足够的字符进行交换，也就是最长不能超过该字符的个数
     */
    public static int maxRepOpt1(String text) {
        char[] array = text.toCharArray();
        int result = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            result = Math.max(result, helper(array, c));
        }
        return result;
    }

    private static int helper(char[] array, char cur) {
        int f = 0, g = 0;
        int count = 0, result = 0;
        for (char c : array) {
            if (c == cur) {
                ++f;
                ++g;
                ++count;
            } else {
                g = f + 1;
                f = 0;
            }
            result = Math.max(result, g);
        }

        // 最长不超过字符个数
        return Math.min(result, count);
    }
}
