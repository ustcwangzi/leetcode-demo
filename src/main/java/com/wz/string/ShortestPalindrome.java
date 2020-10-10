package com.wz.string;

/**
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 *
 * Example 2:
 * Input: "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome {
    public static void main(String[] args) {
        System.out.println(shortestPalindrome("abbac"));
        System.out.println(shortestPalindrome("adcba"));
    }

    /**
     * 1. 使用双指针 i 和 j 找到第一个不是回文的下标 i
     *    i 从前向后遍历，j 从后向前遍历，若 s[i] == s[j] 则 i++、j--，否则 j--，直到 j<0
     * 2. 如果 i == s.length()，说明此时整个 s 是回文，直接返回 s
     * 3. 否则，i 及之后的部分肯定不是回文，取出这部分作为 right，同时将其翻转作为 left，对于 mid 即 s[0...i)，可能是回文也可能不是，
     *    对于 mid 需要重复以上过程，继续判断
     *    如 "abbac"，第一次遍历结束时 i=4、right=c、left=c、mid=abba，此时 mid 是回文，第二次遍历直接返回 mid
     *               最终结果为 c abba c
     *    而 "abcba"，第一次遍历结束时 i=2、right=cba、left=abc、mid=ad，此时 mid 不是回文
     *               第二次遍历结束时 i=1、right=d、left=d、mid=a，此时 mid 是回文，第三次遍历结束时直接返回 mid
     *               最终结果为 abc dad cba
     * 4. 最终结果是 left + mid + right
     */
    public static String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int i = 0, j = s.length() - 1;
        while (j >= 0) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
            j--;
        }
        if (i == s.length()) {
            return s;
        }

        String right = s.substring(i);
        String left = new StringBuilder(right).reverse().toString();
        String mid = shortestPalindrome(s.substring(0, i));
        return left + mid + right;
    }
}
