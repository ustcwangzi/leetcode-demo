package com.wz.other;

import java.util.Arrays;

/**
 * You are given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it a palindrome.
 * Return the length of the maximum length awesome substring of s.
 *
 * Example 1:
 * Input: s = "3242415"
 * Output: 5
 * Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.
 *
 * Example 2:
 * Input: s = "12345678"
 * Output: 1
 *
 * Example 3:
 * Input: s = "213123"
 * Output: 6
 * Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists only of digits.
 */
public class FindLongestAwesomeSubstring {
    public static void main(String[] args) {
        System.out.println(longestAwesome("32424113"));
        System.out.println(longestAwesome("12345678"));
        System.out.println(longestAwesome("213123"));
    }

    /**
     * BitMask，和 {@link com.wz.string.FindLongestSubstringContainingVowelsInEvenCounts} 思路类似
     * 允许任意交换，因此只需要关注每个数字出现的奇偶性即可，共有 2^10 中组合方式，组成回文串的条件就是最多只有一种数字出现次数为奇数
     * 使用长度为 1024 的数组记录每种组合方式最早出现的位置，每种组合方式使用二进制的形式来表示
     * 遍历字符串，若当前 mask 出现过，则更新结果，另外允许有有一个数字出现奇数次，因此还需要从 0～9 进行反转，判断有没有出现过
     * 以 32424113 为例说明该过程
     * i    s[i]    pre         mask        result
     * -1   -       -           000000      0
     * 0    3       3           001000      j=3时, 0-(-1)=1
     * 1    2       32          001100      j=2时, 1-0=1
     * 2    4       324         011100      j=4时, 2-1=1
     * 3    2       3242        011000      j=4时, 3-0=3
     * 4    4       32424       001000      j=3时, 4-(-1)=5
     * 5    1       324241      001010      j=1时, 5-0=5
     * 6    1       3242411     001000      j=3时, 6-(-1)=7
     * 7    3       32424113    000000      7-(-1)=8
     */
    public static int longestAwesome(String s) {
        int n = s.length(), result = 0, mask = 0;
        int[] array = new int[1024];
        Arrays.fill(array, n);
        // 解决整个字符串满足条件的情况
        array[0] = -1;

        for (int i = 0; i < n; i++) {
            mask ^= 1 << (s.charAt(i) - '0');
            // mask 之前出现过，则更新结果
            result = Math.max(result, i - array[mask]);
            // 允许有一个数字出现奇数次
            for (int j = 0; j < 10; j++) {
                result = Math.max(result, i - array[mask ^ (1 << j)]);
            }
            // 记录 mask 第一次出现的位置
            array[mask] = Math.min(array[mask], i);
        }
        return result;
    }
}
