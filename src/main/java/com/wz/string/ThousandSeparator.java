package com.wz.string;

/**
 * Given an integer n, add a dot (".") as the thousands separator and return it in string format.
 *
 * Example 1:
 * Input: n = 987
 * Output: "987"
 *
 * Example 2:
 * Input: n = 1234
 * Output: "1.234"
 *
 * Constraints:
 * 0 <= n < 2^31
 */
public class ThousandSeparator {
    public static void main(String[] args) {
        System.out.println(thousandSeparator(1234));
    }

    /**
     * 每三个数字加上一个点
     */
    public static String thousandSeparator(int n) {
        if (n == 0) {
            return String.valueOf(0);
        }
        StringBuilder builder = new StringBuilder();
        int count = 0;
        while (n != 0) {
            if (count == 3) {
                builder.append(".");
                count = 0;
            }
            builder.append(n % 10);
            n /= 10;
            count++;
        }
        return builder.reverse().toString();
    }
}
