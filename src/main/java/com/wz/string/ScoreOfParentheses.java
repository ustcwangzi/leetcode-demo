package com.wz.string;

import java.util.Stack;

/**
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 * 1. () has score 1
 * 2. AB has score A + B, where A and B are balanced parentheses strings.
 * 3. (A) has score 2 * A, where A is a balanced parentheses string.
 *
 * Example 1:
 * Input: "()"
 * Output: 1
 *
 * Example 2:
 * Input: "()()"
 * Output: 2
 *
 * Example 3:
 * Input: "(()(()))"
 * Output: 6
 *
 * Note:
 * 1. S is a balanced parentheses string, containing only ( and ).
 * 2. 2 <= S.length <= 50
 */
public class ScoreOfParentheses {
    public static void main(String[] args) {
        System.out.println(scoreOfParentheses("(()(()))"));
    }

    /**
     * 思路与 {@link ValidParentheses} 类似
     * 当遇到左括号时，将当前的分数压入栈中，并把当前得分清0，若遇到的是右括号，说明此时已经形成了一个完整的合法的括号字符串了，
     * 而且除去外层的括号，内层的得分就是当前的结果 result，此时就要乘以2，并且要跟1比较，取二者中的较大值
     * 这是因为假如中间是空串，那么返回值是0，乘以2还是0，但是 "()" 的分值应该是1，所以累加的时候要跟1做比较
     * 同时还要加上栈顶的值，因为栈顶的值是之前合法括号子串的值，跟当前的是并列关系，所以是相加的操作
     * 以 (()(())) 为例说明以上过程
     * S    stack   result
     * (    0       0
     * (    00      0
     * )    0       1
     * (    01      0
     * (    010     0
     * )    01      1
     * )    0       3
     * )            6
     */
    public static int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        char[] array = S.toCharArray();
        int result = 0;
        for (char cur : array) {
            if (cur == '(') {
                stack.add(result);
                result = 0;
            } else {
                result = stack.pop() + Math.max(result * 2, 1);
            }
        }
        return result;
    }
}
