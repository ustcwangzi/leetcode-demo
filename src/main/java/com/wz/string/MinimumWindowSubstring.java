package com.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * Note:
 * 1. If there is no such window in S that covers all characters in T, return the empty string "".
 * 2. If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    /**
     * 滑动窗口
     * 使用 countMap 记录组成 t 所需每个字符的次数
     * 然后遍历 s，每遍历一个字符，将其在 countMap 中的次数减1，如果减1之后的次数大于等于0，说明该字符可作为 t 中的一部分，将 count--
     * 当 count == 0 时，说明遍历的字符已能够组成 t，记录组成 t 的最小字符长度以及开始位置
     * 同时将左指针 left 向右滑动，每滑动一个字符，将其在 countMap 中的次数加1，代表将该字符去除，
     * 那么对应的 countMap 中的字符相应增加，如果加1之后的结果大于0，说明该字符是组成 t 所必须的，因此将 count+1
     */
    public static String minWindow(String s, String t) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            countMap.put(t.charAt(i), countMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        // left: 左指针，start: 结果的开始位置，count: 组成 t 还缺少的字符个数
        int left = 0, start = 0, minLength = Integer.MAX_VALUE, count = t.length();
        for (int i = 0; i < s.length(); i++) {
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) - 1);
            if (countMap.get(s.charAt(i)) >= 0) {
                count--;
            }

            while (count == 0) {
                if (minLength > i - left + 1) {
                    minLength = i - left + 1;
                    start = left;
                }

                countMap.put(s.charAt(left), countMap.getOrDefault(s.charAt(left), 0) + 1);
                if (countMap.get(s.charAt(left)) > 0) {
                    count++;
                }
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }
}
