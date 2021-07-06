package com.wz.practice;

import java.util.Stack;

/**
 * 请写一个整数计算器，支持加减乘除和括号。
 * 示例1
 * 输入： "1+2"
 * 返回值：3
 *
 * 示例2
 * 输入："(2*(3-4))*5"
 * 返回值：-10
 *
 * 示例3
 * 输入： "3+2*3*4-1"
 * 返回值：26
 */
public class Calculator {
    public static void main(String[] args) {
        System.out.println(calculate("(2*(3-4))*5"));
        System.out.println(calculate("3+2*3*4-1"));
    }

    /**
     * 与 {@link com.wz.math.BasicCalculator} 类似，只是运算符增多了，可以增加栈来进行计算
     */
    public static int calculate(String s) {
        int num = 0, n = s.length();
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == ' ') {
                continue;
            }
            if (cur >= '0' && cur <= '9') {
                num = 10 * num + (cur - '0');
            } else if (cur == '(') {
                // 递归处理括号内的表达式
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

            if (cur == '+' || cur == '-' || cur == '*' || cur == '/' || i == n - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = cur;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
