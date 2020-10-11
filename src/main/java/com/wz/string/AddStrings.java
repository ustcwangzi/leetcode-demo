package com.wz.string;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 * 1. The length of both num1 and num2 is < 5100.
 * 2. Both num1 and num2 contains only digits 0-9.
 * 3. Both num1 and num2 does not contain any leading zero.
 * 4. You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddStrings {
    public static void main(String[] args) {
        System.out.println(addStrings("999", "1"));
    }

    /**
     * 在遍历的过程中进行相加，同时记录进位 carry
     */
    public static String addStrings(String num1, String num2) {
        char[] array1 = num1.toCharArray(), array2 = num2.toCharArray();
        // 进位
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        int i = array1.length, j = array2.length;
        while (i > 0 && j > 0) {
            int sum = array1[--i] - '0' + array2[--j] - '0' + carry;
            carry = sum / 10;
            builder.append(sum % 10);
        }
        while (i > 0) {
            int sum = array1[--i] - '0' + carry;
            carry = sum / 10;
            builder.append(sum % 10);
        }
        while (j > 0) {
            int sum = array2[--j] - '0' + carry;
            carry = sum / 10;
            builder.append(sum % 10);
        }
        if (carry > 0) {
            builder.append(carry);
        }

        return builder.reverse().toString();
    }
}
