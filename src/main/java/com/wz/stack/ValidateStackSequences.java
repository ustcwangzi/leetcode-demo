package com.wz.stack;

import java.util.Stack;

/**
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have been
 * the result of a sequence of push and pop operations on an initially empty stack.
 *
 * Example 1:
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * Example 2:
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 *
 * Constraints:
 * 1. 0 <= pushed.length == popped.length <= 1000
 * 2. 0 <= pushed[i], popped[i] < 1000
 * 3. pushed is a permutation of popped.
 * 4. pushed and popped have distinct values.
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }

    /**
     * 使用栈来模拟
     * 使用 index 记录 popped 中元素的位置，遍历 pushed，若栈不空且栈顶元素等于 popped[index]，则出栈同时 index++
     * 然后将当前遍历的 pushed 元素入栈，遍历结束后，依次判断栈顶元素和 popped 对应位置元素是否相等，不相等直接返回 false
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int n = popped.length, index = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
            stack.push(pushed[i]);
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != popped[index++]) {
                return false;
            }
        }
        return true;
    }
}
