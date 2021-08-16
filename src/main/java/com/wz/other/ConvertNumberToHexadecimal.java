package com.wz.other;

/**
 * Given an integer num, return a string representing its hexadecimal representation. For negative integers, two’s complement method is used.
 * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 *
 * Example 1:
 * Input: num = 26
 * Output: "1a"
 *
 * Example 2:
 * Input: num = -1
 * Output: "ffffffff"
 *
 * Constraints:
 * -2^31 <= num <= 2^31 - 1
 */
public class ConvertNumberToHexadecimal {
    public static void main(String[] args) {
        System.out.println(toHex(0));
        System.out.println(toHex(26));
        System.out.println(toHex(-1));
    }

    /**
     * 把 int 转换为二进制，每 4 位可以代表一位 16 进制
     * 同时，转换为二进制再转换为十进制的好处是不用考虑负数的情况，因为二进制会自动转换为补码
     */
    public static String toHex(int num) {
        StringBuilder builder = new StringBuilder();
        char[] map = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int i = 0; i < 8 && num != 0; i++) {
            // 直接插入到头部，结果不用再反转
            builder.insert(0, map[num & 0xf]);
            num >>= 4;
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }
}
