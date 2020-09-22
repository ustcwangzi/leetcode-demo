package com.wz.math;

/**
 * Given two strings S and T, each of which represents a non-negative rational number,
 * return True if and only if they represent the same number.
 * The strings may use parentheses to denote the repeating part of the rational number.
 * In general a rational number can be represented using up to three parts: an integer part,
 * a non-repeating part, and a repeating part. The number will be represented in one of the following three ways:
 * <IntegerPart> (e.g. 0, 12, 123)
 * <IntegerPart><.><NonRepeatingPart>  (e.g. 0.5, 1., 2.12, 2.0001)
 * <IntegerPart><.><NonRepeatingPart><(><RepeatingPart><)> (e.g. 0.1(6), 0.9(9), 0.00(1212))
 * The repeating portion of a decimal expansion is conventionally denoted within a pair of round brackets.  For example:
 * 1 / 6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66)
 * Both 0.1(6) or 0.1666(6) or 0.166(66) are correct representations of 1 / 6.
 *
 * Example 1:
 * Input: S = "0.(52)", T = "0.5(25)"
 * Output: true
 * Explanation:
 * Because "0.(52)" represents 0.52525252..., and "0.5(25)" represents 0.52525252525..... , the strings represent the same number.
 *
 * Example 2:
 * Input: S = "0.1666(6)", T = "0.166(66)"
 * Output: true
 *
 * Note:
 * Each part consists only of digits.
 * The <IntegerPart> will not begin with 2 or more zeros.  (There is no other restriction on the digits of each part.)
 * 1 <= <IntegerPart>.length <= 4
 * 0 <= <NonRepeatingPart>.length <= 4
 * 1 <= <RepeatingPart>.length <= 4
 */
public class EqualRationalNumbers {
    public static void main(String[] args) {
        System.out.println(isRationalEqual("0.(52)", "0.5(25)"));
        System.out.println(isRationalEqual("0.1666(6)", "0.166(66)"));
    }

    /**
     * 因为题目规定所给的数字都比较短，包括循环部分在内都不会超过12位，所以可以通过对输入的String进行处理，使它们到达一定长度，
     * 然后用 Double.parseDouble(String) 将其转换为double，然后比较是否相等
     */
    public static boolean isRationalEqual(String S, String T) {
        return getDouble(S) == getDouble(T);
    }

    private static double getDouble(String s) {
        int repeatStart = s.indexOf('(') + 1;
        String repeat = repeatStart == 0 ? "" : s.substring(repeatStart, s.length() - 1);
        StringBuilder builder = new StringBuilder(repeatStart == 0 ? s : s.substring(0, repeatStart - 1));
        for (int i = 0; i < 20; ++i) {
            builder.append(repeat);
        }
        s = builder.toString();
        return Double.parseDouble(s);
    }
}
