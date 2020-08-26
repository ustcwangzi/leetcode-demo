package com.wz.math;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 *
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("123", "456"));
    }

    /**
     * 两数相乘，结果的长度不会大于两数长度和 m + n，用 int[m + n] result 记录
     * 接下来对 num1 和 num2 做一个双重循环从后向前遍历
     * 1. 当前的 digit1 = num1.charAt(i) - '0'， digit2 = num2.charAt(j) - '0'
     * 2. 这时可以更新当前 result[i + j + 1] 这个位置为原来存在这一位置上的值再加上新的值 digits 1 * digit2
     *    result[i + j + 1] += digits 1 * digit2
     * 3. 接下来根据 result[i + j + 1] 的新值，更新高一位的 result[i + j]， result[i + j] += result[i + j + 1]/10，就是本来的值加上进位
     * 4. 最后再用 result[i + j + 1] %= 10 求出这一位置进位后剩下的 digit
     * 求出 result 数组之后使用 StringBuilder 来从头遍历数组，求出最终结果
     */
    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        int m = num1.length(), n = num2.length();
        int[] result = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                result[i + j + 1] += digit1 * digit2;
                result[i + j] += result[i + j + 1] / 10;
                result[i + j + 1] %= 10;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int cur : result) {
            // 开头的 0 值，需要跳过
            if (builder.length() == 0 && cur == 0) {
                continue;
            }
            builder.append(cur);
        }

        // 遍历完毕依然等于0，返回"0"
        if (builder.length() == 0) {
            builder.append(0);
        }
        return builder.toString();
    }
}
