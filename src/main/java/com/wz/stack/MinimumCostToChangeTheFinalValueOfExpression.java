package com.wz.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a valid boolean expression as a string expression consisting of the characters '1','0','&' (bitwise AND operator),'|' (bitwise OR operator),'(', and ')'.
 * For example, "()1|1" and "(1)&()" are not valid while "1", "(((1))|(0))", and "1|(0&(1))" are valid expressions.
 * Return the minimum cost to change the final value of the expression.
 * For example, if expression = "1|1|(0&0)&1", its value is 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1. We want to apply operations so that the new expression evaluates to 0.
 * The cost of changing the final value of an expression is the number of operations performed on the expression. The types of operations are described as follows:
 * - Turn a '1' into a '0'.
 * - Turn a '0' into a '1'.
 * - Turn a '&' into a '|'.
 * - Turn a '|' into a '&'.
 * Note: '&' does not take precedence over '|' in the order of calculation. Evaluate parentheses first, then in left-to-right order.
 *
 * Example 1:
 * Input: expression = "1&(0|1)"
 * Output: 1
 * Explanation: We can turn "1&(0|1)" into "1&(0&1)" by changing the '|' to a '&' using 1 operation.
 * The new expression evaluates to 0.
 *
 * Example 2:
 * Input: expression = "(0&0)&(0&0&0)"
 * Output: 3
 * Explanation: We can turn "(0&0)&(0&0&0)" into "(0|1)|(0&0&0)" using 3 operations.
 * The new expression evaluates to 1.
 *
 * Example 3:
 * Input: expression = "(0|(1|0&1))"
 * Output: 1
 * Explanation: We can turn "(0|(1|0&1))" into "(0|(0|0&1))" using 1 operation.
 * The new expression evaluates to 0.
 *
 *
 * Constraints:
 * 1. 1 <= expression.length <= 10^5
 * 2. expression only contains '1','0','&','|','(', and ')'
 * 3. All parentheses are properly matched.
 * 4. There will be no empty parentheses (i.e: "()" is not a substring of expression).
 */
public class MinimumCostToChangeTheFinalValueOfExpression {
    public static void main(String[] args) {
        System.out.println(minOperationsToFlip("1&(0|1)"));
        System.out.println(minOperationsToFlip("(0&0)&(0&0&0)"));
        System.out.println(minOperationsToFlip("(0|(1|0&1))"));
    }

    /**
     * 设计 3 个栈，分别存储表达式计算的结果值、反转当前表达式的最小操作次数和符号
     * 然后遍历字符串，依次计算 [0, 当前位置] 区间内字符串表达式的结果值、反转所需的最小操作次数，直至完成遍历
     */
    public static int minOperationsToFlip(String expression) {
        // 当前表达式的结果值
        Deque<Integer> numStack = new LinkedList<>();
        // 反转当前表达式的最小操作次数，与表达式结果栈中的元素一一对应
        Deque<Integer> operationStack = new LinkedList<>();
        // 符号
        Deque<Character> expressStack = new LinkedList<>();
        for (char express : expression.toCharArray()) {
            // 除')'以外的符号直接入栈
            if (express == '(' || express == '&' || express == '|') {
                expressStack.offerFirst(express);
                continue;
            }

            // 遇到右括号，弹出上一左括号，触发后续计算
            if (express == ')') {
                expressStack.pollFirst();
            } else {
                // 遇到数字，入栈
                numStack.offerFirst(express - '0');
                // 任意单个数值反转操作次数为 1
                operationStack.offerFirst(1);
            }

            // 计算当前可处理的表达式部分
            if (numStack.size() > 1 && expressStack.peekFirst() != '(') {
                int[] result = minOperation(numStack.pollFirst(), numStack.pollFirst(), operationStack.pollFirst(), operationStack.pollFirst(), expressStack.pollFirst());
                numStack.offerFirst(result[0]);
                operationStack.offerFirst(result[1]);
            }
        }
        // 反转操作次数栈此时只剩1个元素，就是整个表达式反转的最小操作次数
        return operationStack.peekFirst();
    }

    /**
     * 对计算当前表达式中的两个元素，计算值和最小反转操作次数
     */
    private static int[] minOperation(int num1, int num2, int operation1, int operation2, char express) {
        if (express == '&') {
            // 1&1,将其中一个1反转为0
            if (num1 == 1 && num2 == 1) {
                return new int[]{1, Math.min(operation1, operation2)};
            }
            // 0&0,将其中一个0反转为1,并将&反转为|
            if (num1 == 0 && num2 == 0) {
                return new int[]{0, Math.min(operation1, operation2) + 1};
            }
            // 1&0,将&反转为|
            return new int[]{0, 1};
        }
        // 0|0，将其中一个0反转为1
        if (num1 == 0 && num2 == 0) {
            return new int[]{0, Math.min(operation1, operation2)};
        }
        // 1|1,将其中一个1反转为0，并将|反转为&
        if (num1 == 1 && num2 == 1) {
            return new int[]{1, Math.min(operation1, operation2) + 1};
        }
        // 1|0,将|反转为&
        return new int[]{1, 1};
    }
}
