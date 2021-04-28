package com.wz.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').
 * Notice that s contains uppercase and lowercase letters.
 * Return true if a and b are alike. Otherwise, return false.
 *
 * Example 1:
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 *
 * Example 2:
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
 * Notice that the vowel o is counted twice.
 *
 * Constraints:
 * 1. 2 <= s.length <= 1000
 * 2. s.length is even.
 * 3. s consists of uppercase and lowercase letters.
 */
public class DetermineIfStringHalvesAreAlike {
    public static void main(String[] args) {
        System.out.println(halvesAreAlike("book"));
        System.out.println(halvesAreAlike("textbook"));
    }

    /**
     * 遍历 s，使用 count 记录元音字符个数，若遇到元音字符，前半部分对 count++，后半部分对 count--，最后判断 count 是否等于 0
     */
    public static boolean halvesAreAlike(String s) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int mid = s.length() / 2, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < mid) {
                count += set.contains(s.charAt(i)) ? 1 : 0;
            } else {
                count -= set.contains(s.charAt(i)) ? 1 : 0;
            }
        }
        return count == 0;
    }
}
