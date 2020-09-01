package com.wz.math;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 * Input: " 2 - 1 + 2 "
 * Output: 3
 *
 * Example 2:
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate("2 - 1 + 2"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    /**
     * 用 count 处理括号匹配，遇到左括号自增1，遇到右括号自减1，当 count 为0的时候，说明括号正好完全匹配
     * 然后根据左右括号的位置提取出中间的子字符串递归调用 calculate，返回值赋给 num
     */
    public static int calculate(String s) {
        int result = 0, num = 0, sign = 1, n = s.length();
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur >= '0' && cur <= '9') {
                // 将字符串处理成数字
                num = 10 * num + (cur - '0');
            } else if (cur == '(') {
                // 处理括号
                int left = i, count = 0;
                while (i < n) {
                    if (s.charAt(i) == '(') {
                        count++;
                    } else if (s.charAt(i) == ')') {
                        count--;
                    }
                    if (count == 0) {
                        break;
                    }
                    i++;
                }
                num = calculate(s.substring(left + 1, i));
            }
            // 处理加法和减法
            if (cur == '+' || cur == '-' || i == n - 1) {
                result += sign * num;
                num = 0;
                sign = (cur == '+') ? 1 : -1;
            }
        }

        return result;
    }
}
