package com.wz.binarysearch;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
 *
 * Example 1:
 * Input: s = "banana"
 * Output: "ana"
 *
 * Example 2:
 * Input: s = "abcd"
 * Output: ""
 *
 * Constraints:
 * 1. 2 <= s.length <= 3 * 10^4
 * 2. s consists of lowercase English letters.
 */
public class LongestDuplicateSubstring {
    public static void main(String[] args) {
        System.out.println(longestDupSubstring("banana"));
        System.out.println(longestDupSubstring("abcd"));
    }

    /**
     * 最长重复子串，二分查找找到可能的子串长度，然后再找到两个相等的长度为 len 的子串
     * 为了加快查找重复子串，可以将字符串转换为数字，然后使用 map 记录对应的出现位置
     */
    public static String longestDupSubstring(String s) {
        int left = 1, right = s.length() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int index = rabinKarp(s, mid);
            if (index == -1) {
                right = mid;
            } else {
                left = mid;
            }
        }

        int leftIndex = rabinKarp(s, left), rightIndex = rabinKarp(s, right);
        if (rightIndex != -1) {
            return s.substring(rightIndex, rightIndex + right);
        }
        if (leftIndex != -1) {
            return s.substring(leftIndex, leftIndex + left);
        }
        return "";
    }

    private static final int MOD = 1_000_000_007, BASE = 31;

    private static int rabinKarp(String source, int len) {
        long pow = 1L;
        // code -> index
        for (int i = 0; i < len; i++) {
            pow = (pow * BASE) % MOD;
        }

        Map<Long, Integer> map = new HashMap<>();
        long curCode = 0;
        for (int i = 0; i < source.length(); ++i) {
            curCode = (curCode * BASE + source.charAt(i)) % MOD;
            if (i < len - 1) {
                continue;
            }
            if (i >= len) {
                curCode -= (pow * source.charAt(i - len)) % MOD;
                if (curCode < 0) {
                    curCode += MOD;
                }
            }

            int curIndex = i - len + 1;
            if (map.containsKey(curCode)) {
                int preIndex = map.get(curCode);
                String s1 = source.substring(preIndex, preIndex + len), s2 = source.substring(curIndex, i + 1);
                if (s1.equals(s2)) {
                    return preIndex;
                }
            }
            map.put(curCode, curIndex);
        }
        return -1;
    }
}
