package com.wz.math;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 *
 * Example 1:
 * Input: 1
 * Output: "A"
 *
 * Example 2:
 * Input: 28
 * Output: "AB"
 *
 * Example 3:
 * Input: 701
 * Output: "ZY"
 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        System.out.println(convertToTitle(26));
        System.out.println(convertToTitle(701));
    }

    /**
     * 本质上就是将一个 10 进制数转换为一个 26 进制的数
     * 由于下标从 1 开始而不是从 0 开始，因此要减一操作
     */
    public static String convertToTitle(int n) {
        String result = "";
        while (n != 0) {
            result = (char) (--n % 26 + 'A') + result;
            n /= 26;
        }
        return result;
    }
}
