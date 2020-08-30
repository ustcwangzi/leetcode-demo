package com.wz.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * If multiple answers are possible, just return any of them.
 *
 * Example 1:
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 *
 * Example 2:
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 *
 * Example 3:
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class FractionToRecurringDecimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(1, 2));
        System.out.println(fractionToDecimal(2, 3));
    }

    /**
     * 分子 numerator，分母 denominator，以小数的形式返回它们的结果 result，当有循环小数时，以括号形式表示
     * 分为整数部分和小数部分，重点在于小数部分的处理，因为小数部分有可能会出现循环
     * 使用 HashMap 存储每一次的余数，以及该余数在返回结果 result 中的下标，每一次得到新的余数，就查询该余数是否已经在 HashMap 中
     * 是的话说明产生了循环，那么直接在 result 中该余数对应的位置后面插入'('，result 末尾加上 ')'，结束运算
     * 由于要算出小数每一位，采取的方法是每次把余数乘 10，再除以除数，得到的商即为小数的下一位数字
     */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 0) {
            return "";
        }
        long num = Math.abs((long) numerator), den = Math.abs((long) denominator);
        long out = num / den, remainder = (num % den) * 10;

        // 整数部分
        String result = String.valueOf(out);
        if ((numerator < 0) ^ (denominator < 0)) {
            result = "-" + result;
        }
        if (remainder == 0) {
            return result;
        }

        // 小数部分
        Map<Long, Integer> map = new HashMap<>();
        result += ".";
        while (remainder != 0) {
            // 产生循环
            if (map.containsKey(remainder)) {
                int position = map.get(remainder);
                String part1 = result.substring(0, position);
                String part2 = result.substring(position);
                result = part1 + "(" + part2 + ")";
                return result;
            }

            map.put(remainder, result.length());
            out = remainder / den;
            result += String.valueOf(out);
            remainder = (remainder % den) * 10;
        }
        return result;
    }
}
