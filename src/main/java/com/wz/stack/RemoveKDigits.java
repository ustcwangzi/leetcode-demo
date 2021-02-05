package com.wz.stack;

import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 *
 * Example 1:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 *
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("112", 1));
    }

    /**
     * 如何保证一个整数比较小，显然高位数字越小，这个数越小，这个数如果从高位到低是升序，那么这个数一定比其他的排列方式要小，如 123456789。
     * 那么本题的题意可以理解为，删除一个数中的几个数字，尽量保持删除后的数字是一个升序的状态（维护一个升序序列），具体方案如下：
     * 使用栈来维护一个非降序序列，没遍历一个字符，就和栈顶进行比较，如果小于栈顶，则出栈，直到要压入栈的数小于等于栈顶；
     * 没出栈一个元素，k--，如果 k == 0，则不再比较，直接压入栈中；
     * 如果当前要压入的元素为 "0"，并且栈为空，那么跳过这个元素，因为最终结果不能以 0 开始；
     * 遍历结束后，如果 k > 0，则继续出栈，直到 k == 0，此时删除的都是尾部比较大的元素；
     * 最后将元素采用头插法加入结果集，然后返回
     */
    public static String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        char[] array = num.toCharArray();
        for (char cur : array) {
            while (!stack.isEmpty() && stack.peek() > cur && k > 0) {
                stack.pop();
                k--;
            }
            if (stack.isEmpty() && cur == '0') {
                continue;
            }
            stack.push(cur);
        }
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }

        if (stack.isEmpty()) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }
        return builder.toString();
    }
}
