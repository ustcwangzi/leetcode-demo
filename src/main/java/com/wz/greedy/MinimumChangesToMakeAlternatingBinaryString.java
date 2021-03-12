package com.wz.greedy;

/**
 * You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.
 * The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.
 * Return the minimum number of operations needed to make s alternating.
 *
 * Example 1:
 * Input: s = "0100"
 * Output: 1
 * Explanation: If you change the last character to '1', s will be "0101", which is alternating.
 *
 * Example 2:
 * Input: s = "1111"
 * Output: 2
 * Explanation: You need two operations to reach "0101" or "1010".
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^4
 * 2. s[i] is either '0' or '1'.
 */
public class MinimumChangesToMakeAlternatingBinaryString {
    public static void main(String[] args) {
        System.out.println(minOperations("0100"));
        System.out.println(minOperations("1111"));
        System.out.println(minOperations("10010100"));
    }

    /**
     * 结果有两种可能 0101... 或 1010...，即一种是偶数位置都是 0、奇数位置都是 1，另一种是偶数位置都是 1、奇数位置都是 0
     * 分别求出这两种情况下需要修改的字符个数，然后取其中的较小值即可
     */
    public static int minOperations(String s) {
        // 分别代表偶数位置为 0、奇数位置为 0
        int count0 = 0, count1 = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (i % 2 == 0) {
                // 偶数位置
                if (cur != '0') {
                    count0++;
                } else {
                    count1++;
                }
            } else {
                // 奇数位置
                if (cur != '0') {
                    count1++;
                } else {
                    count0++;
                }
            }
        }
        return Math.min(count0, count1);
    }
}
