package com.wz.greedy;

/**
 * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 10^9 + 7.
 * A string is homogenous if all the characters of the string are the same.
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: s = "abbcccaa"
 * Output: 13
 * Explanation: The homogenous substrings are listed as below:
 * "a"   appears 3 times.
 * "aa"  appears 1 time.
 * "b"   appears 2 times.
 * "bb"  appears 1 time.
 * "c"   appears 3 times.
 * "cc"  appears 2 times.
 * "ccc" appears 1 time.
 * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
 *
 * Example 2:
 * Input: s = "zzzzz"
 * Output: 15
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of lowercase letters.
 */
public class CountNumberOfHomogenousSubstrings {
    public static void main(String[] args) {
        System.out.println(countHomogenous("abbcccaa"));
    }

    /**
     * 对一段长度为 n 的由相同字符构成的字符串，其同构子字符串的数量为 1 + 2 + … + n = n * (n + 1) / 2，直接计算即可
     */
    public static int countHomogenous(String s) {
        char[] array = s.toCharArray();
        int i = 0, result = 0, mod = 1000000007;
        while (i < array.length) {
            int count = 1;
            // 统计相同字符个数
            while (i + count < array.length && array[i + count] == array[i]) {
                count++;
            }
            // 计算结果
            result += ((count * (count + 1L)) % mod) >> 1;
            i += count;
        }
        return result;
    }
}
