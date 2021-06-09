package com.wz.twopointer;

import java.util.Arrays;

/**
 * Given two strings s1 and s2, return true if s2 contains the permutation of s1.
 * In other words, one of s1's permutations is the substring of s2.
 *
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 * Constraints:
 * 1. 1 <= s1.length, s2.length <= 10^4
 * 2. s1 and s2 consist of lowercase English letters.
 */
public class PermutationInString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }

    /**
     * 双指针
     * 判断 s2 中是否包含 s1 的任意一种排列组合，使用 count1 记录 s1 中每个元素次数，使用 count2 记录 s2[left...right] 元素次数
     * 如果 right-left+1 == s1.length 说明长度一致，这时判断两个数组元素是否相等，相等则直接返回 false，否则 left 右移
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] count1 = new int[26], count2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
        }
        int left = 0, right = 0;
        while (right < s2.length()) {
            count2[s2.charAt(right) - 'a']++;

            if (right - left + 1 == s1.length()) {
                if (Arrays.equals(count1, count2)) {
                    return true;
                }
                count2[s2.charAt(left) - 'a']--;
                left++;
            }

            right++;
        }
        return false;
    }
}
