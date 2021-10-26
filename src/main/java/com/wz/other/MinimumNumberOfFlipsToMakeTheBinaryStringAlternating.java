package com.wz.other;

/**
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
 * 1. Type-1: Remove the character at the start of the string s and append it to the end of the string.
 * 2. Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
 * Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
 * The string is called alternating if no two adjacent characters are equal.
 * For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Example 1:
 * Input: s = "111000"
 * Output: 2
 * Explanation: Use the first operation two times to make s = "100011".
 * Then, use the second operation on the third and sixth elements to make s = "101010".
 *
 * Example 2:
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating.
 *
 * Example 3:
 * Input: s = "1110"
 * Output: 1
 * Explanation: Use the second operation on the second element to make s = "1010".
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s[i] is either '0' or '1'.
 */
public class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {
    public static void main(String[] args) {
        System.out.println(minFlips("111000"));
        System.out.println(minFlips("1110"));
    }

    /**
     * 对操作 1，将第一个字符移动到字符串结尾，等价于将 s 首尾相接构造一个长度为 2 * n 的字符串，再通过长度为 n 的滑动窗口取最小值
     * 最终得到的交替字符串只能是 0101010... 或 1010101... 两种情况，对这两种情况分别求解所需的操作次数，然后取其中的最小值即可
     */
    public static int minFlips(String s) {
        int n = s.length(), result = Integer.MAX_VALUE;
        s += s;
        // 构造 0101... 和 1010... 的前 i 位所需操作次数
        int[] zeroStart = new int[s.length() + 1], oneStart = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if ((i & 1) == s.charAt(i) - '0') {
                // 偶数位为0 或 奇数位为1
                zeroStart[i + 1] = zeroStart[i];
                oneStart[i + 1] = oneStart[i] + 1;
            } else {
                // 偶数位为1 或 奇数位为0
                zeroStart[i + 1] = zeroStart[i] + 1;
                oneStart[i + 1] = oneStart[i];
            }
        }
        for (int i = n; i <= s.length(); i++) {
            result = Math.min(result, zeroStart[i] - zeroStart[i - n]);
            result = Math.min(result, oneStart[i] - oneStart[i - n]);
        }
        return result;
    }
}
