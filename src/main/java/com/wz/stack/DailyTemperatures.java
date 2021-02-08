package com.wz.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note:
 * The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70})));
    }

    /**
     * 单调栈，与 {@link NextGreaterElementI} 类似
     * 只允许更小的元素入栈，当前元素大于栈顶，则出栈，同时计算结果
     */
    public static int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int top = stack.pop();
                result[top] = i - top;
            }
            stack.push(i);
        }
        return result;
    }
}
