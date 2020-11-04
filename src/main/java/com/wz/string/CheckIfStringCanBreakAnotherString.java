package com.wz.string;

import java.util.Arrays;

/**
 * Given two strings: s1 and s2 with the same size, check if some permutation of string s1 can break
 * some permutation of string s2 or vice-versa. In other words s2 can break s1 or vice-versa.
 * A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n-1.
 *
 * Example 1:
 * Input: s1 = "abc", s2 = "xya"
 * Output: true
 * Explanation: "ayx" is a permutation of s2="xya" which can break to string "abc" which is a permutation of s1="abc".
 *
 * Example 2:
 * Input: s1 = "abe", s2 = "acd"
 * Output: false
 * Explanation: All permutations for s1="abe" are: "abe", "aeb", "bae", "bea", "eab" and "eba" and all permutation for
 * s2="acd" are: "acd", "adc", "cad", "cda", "dac" and "dca". However, there is not any permutation from s1 which can break some permutation from s2 and vice-versa.
 *
 * Constraints:
 * 1. s1.length == n
 * 2. s2.length == n
 * 3. 1 <= n <= 10^5
 * 4. All strings consist of lowercase English letters.
 */
public class CheckIfStringCanBreakAnotherString {
    public static void main(String[] args) {
        System.out.println(checkIfCanBreak("abc", "xya"));
    }

    /**
     * 把 s1 和 s2 按字符升序重新排列，只需要判断 s1[i] >= s2[i] 或者 s1[i] <= s2[i] 即可
     */
    public static boolean checkIfCanBreak(String s1, String s2) {
        char[] array1 = s1.toCharArray(), array2 = s2.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);

        int lesser = 0, greater = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] < array2[i]) {
                lesser++;
            } else if (array1[i] > array2[i]) {
                greater++;
            }
            if (lesser > 0 && greater > 0) {
                return false;
            }
        }
        return true;
    }
}
