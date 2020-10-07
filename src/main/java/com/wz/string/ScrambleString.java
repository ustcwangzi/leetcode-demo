package com.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * We can scramble a string s to get a string t using the following algorithm:
 * 1. If the length of the string is 1, stop.
 * 2. If the length of the string is > 1, do the following:
 * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
 * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
 * Apply step 1 recursively on each of the two substrings x and y.
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 *
 * Example 1:
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at ranom index each of them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now and the result string is "rgeat" which is s2.
 * As there is one possible scenario that led s1 to be scrambled to s2, we return true.
 *
 * Example 2:
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 */
public class ScrambleString {
    public static void main(String[] args) {
        System.out.println(isScramble("great", "rgeat"));
        System.out.println(isScramble("abcde", "caebd"));
    }

    private static final Map<String, Boolean> PARTIAL_RESULT = new HashMap<>();

    /**
     * s1 和 s2 是 scramble 的话，那么必然存在一个在 s1 上的长度 l1，将 s1 分成 s11 和 s12 两段，
     * 同样有 s21 和 s22，那么要么 s11 和 s21 是 scramble 的并且 s12 和 s22 是 scramble 的；
     * 要么 s11 和 s22 是 scramble 的并且 s12 和 s21 是 scramble 的。
     * 例如 rgeat 和 great，rgeat 可分成 rg 和 eat 两段， great 可分成 gr 和 eat 两段，
     * rg 和 gr 是 scrambled 的， eat 和 eat 当然是 scrambled
     */
    public static boolean isScramble(String s1, String s2) {
        if (s2.equals(s1)) {
            return true;
        }

        int len = s1.length();
        String key = s1 + "-" + s2;
        if (PARTIAL_RESULT.containsKey(key)) {
            return PARTIAL_RESULT.get(key);
        }

        for (int i = 1; i < len; i++) {
            String firstSplit1 = s1.substring(0, i);
            String secondSplit1 = s1.substring(i, len);
            String firstSplit2 = s2.substring(0, i);
            String secondSplit2 = s2.substring(i, len);
            String firstSwapSplit2 = s2.substring(len - i, len);
            String secondSwapSplit2 = s2.substring(0, len - i);
            if ((isScramble(firstSplit1, firstSplit2) && isScramble(secondSplit1, secondSplit2))
                    || isScramble(firstSplit1, firstSwapSplit2) && isScramble(secondSplit1, secondSwapSplit2)) {
                PARTIAL_RESULT.put(key, true);
                return true;
            }
        }

        PARTIAL_RESULT.put(key, false);
        return false;
    }
}
