package com.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it palindrome.
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
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists only of digits.
 */
public class FindLongestAwesomeSubstring {
    public static void main(String[] args) {
        System.out.println(longestAwesome("3242415"));
    }

    /**
     * 只关注每种数字出现次数的奇偶性，故共有 2^10 种组合方式，组成回文串的条件就是最多只有一种数字出现次数为奇数
     * 用 map 记录每种组合方式（用二进制表示）最早出现的位置，初始时，map[0] = -1。维护一个每种数字出现次数的二进制值，初始为 0。
     * 遍历时，当前数位 s[i] 上的二进制值取反，枚举每一个数位，如果取反后，在 map 中能查到值为 p，则说明 [p + 1, i] 区间可以构成回文串。
     * 如果当前二进制值本身也在 map 中出现过，记为 p，则也说明 [p + 1, i] 可以构成回文串。如果没出现过，则记录到 map 中。
     */
    public static int longestAwesome(String s) {
        int x = 0, result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < s.length(); i++) {
            x = x ^ (1 << (s.charAt(i) - '0'));
            if (map.containsKey(x)) {
                result = Math.max(result, i - map.get(x));
            } else {
                map.put(x, i);
            }

            for (int j = 0; j < 10; j++) {
                if (map.containsKey(x ^ (1 << j))) {
                    result = Math.max(result, i - map.get(x ^ (1 << j)));
                }
            }
        }
        return result;
    }
}
