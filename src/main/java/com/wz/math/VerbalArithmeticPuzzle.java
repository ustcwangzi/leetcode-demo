package com.wz.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an equation, represented by words on left side and the result on right side.
 * You need to check if the equation is solvable under the following rules:
 * 1. Each character is decoded as one digit (0 - 9).
 * 2. Every pair of different characters they must map to different digits.
 * 3. Each words[i] and result are decoded as one number without leading zeros.
 * 4. Sum of numbers on left side (words) will equal to the number on right side (result).
 * Return True if the equation is solvable otherwise return False.
 *
 * Example 1:
 * Input: words = ["SEND","MORE"], result = "MONEY"
 * Output: true
 * Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
 * Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
 *
 * Example 2:
 * Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
 * Output: true
 * Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
 * Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
 */
public class VerbalArithmeticPuzzle {
    public static void main(String[] args) {
        System.out.println(isSolvable(new String[]{"SEND", "MORE"}, "MONEY"));
        System.out.println(isSolvable(new String[]{"SIX", "SEVEN", "SEVEN"}, "TWENTY"));
    }

    /**
     * 递归回溯
     */
    public static boolean isSolvable(String[] words, String result) {
        Set<Character> seen = new HashSet<>();
        boolean[] isFirst = new boolean[128];
        int[] char_count = new int[128];
        int[] pow_num = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!isFirst[c] && i == 0 && word.length() > 1) {
                    isFirst[c] = true;
                }
                char_count[c] += pow_num[word.length() - i - 1];
                seen.add(c);
            }
        }

        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);
            if (!isFirst[c] && i == 0 && result.length() > 1) {
                isFirst[c] = true;
            }
            char_count[c] -= pow_num[result.length() - i - 1];
            seen.add(c);
        }

        char[] chs = new char[seen.size()];
        int idx = 0;
        for (char c : seen) {
            chs[idx++] = c;
        }
        return helper(char_count, isFirst, chs, new boolean[10], 0, 0);
    }

    private static boolean helper(int[] char_count, boolean[] isFirst, char[] chs, boolean[] used, int step, int diff) {
        if (step == chs.length) {
            return diff == 0;
        }
        for (int i = 0; i < 10; i++) {
            char c = chs[step];
            if (used[i] || (i == 0 && isFirst[c])) {
                continue;
            }
            used[i] = true;
            if (helper(char_count, isFirst, chs, used, step + 1, diff + char_count[c] * i)) {
                return true;
            }
            used[i] = false;
        }
        return false;
    }
}
