package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary string s and an integer k.
 * Return True if every binary code of length k is a substring of s. Otherwise, return False.
 *
 * Example 1:
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11".
 *              They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
 *
 * Example 2:
 * Input: s = "00110", k = 2
 * Output: true
 *
 * Constraints:
 * 1. 1 <= s.length <= 5 * 10^5
 * 2. s consists of 0's and 1's only.
 * 3. 1 <= k <= 20
 */
public class CheckIfStringContainsAllBinaryCodesOfSizeK {
    public static void main(String[] args) {
        System.out.println(hasAllCodes("00110110", 2));
    }

    /**
     * 判断 s 中长度为 k 的所有不同的子串是否有 2^k 个，如果是的话，说明 s 中包含所有长度为 k 的二进制子串
     */
    public static boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - k; i++) {
            set.add(s.substring(i, i + k));
        }
        return set.size() == Math.pow(2, k);
    }
}
