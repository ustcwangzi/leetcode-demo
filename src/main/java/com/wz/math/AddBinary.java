package com.wz.math;

/**
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 *
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 * Constraints:
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 */
public class AddBinary {
    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
    }

    /**
     * 每位相加 sum 有四种可能结果：0、1、2、3
     * sum == 0 时，cur = 0, carry = 0
     * sum == 1 时，cur = 1, carry = 0
     * sum == 2 时，cur = 0, carry = 1
     * sum == 3 时，cur = 1, carry = 1
     */
    public static String addBinary(String a, String b) {
        char[] array1 = a.toCharArray(), array2 = b.toCharArray();
        int[] result = new int[Math.max(a.length(), b.length())];
        // 进位
        int carry = 0;
        int i = array1.length - 1, j = array2.length - 1, index = result.length - 1;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += array1[i--] - '0';
            }
            if (j >= 0) {
                sum += array2[j--] - '0';
            }
            if (sum == 1 || sum == 3) {
                result[index] = 1;
            }
            if (sum == 2 || sum == 3) {
                carry = 1;
            } else {
                carry = 0;
            }
            index--;
        }

        StringBuilder builder = new StringBuilder();
        if (carry != 0) {
            builder.append(carry);
        }
        for (int value : result) {
            builder.append(value);
        }
        return builder.toString();
    }
}
