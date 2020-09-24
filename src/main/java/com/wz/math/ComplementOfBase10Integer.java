package com.wz.math;

/**
 * Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary,
 * 11 as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.
 * The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.
 * For example, the complement of "101" in binary is "010" in binary.
 * For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
 *
 * Example 2:
 * Input: 7
 * Output: 0
 * Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
 */
public class ComplementOfBase10Integer {
    public static void main(String[] args) {
        System.out.println(bitwiseComplement(5));
        System.out.println(bitwiseComplement(7));
    }

    /**
     * 将N转为二进制字符串，利用循环，将其中的0转为1，1转为0，变成新的二进制字符串，再将其转为十进制整数
     */
    public static int bitwiseComplement(int N) {
        String str = Integer.toBinaryString(N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i) == '0' ? '1' : '0');
        }

        return Integer.parseInt(sb.toString(), 2);
    }
}
