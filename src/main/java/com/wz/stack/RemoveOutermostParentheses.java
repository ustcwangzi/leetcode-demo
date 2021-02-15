package com.wz.stack;

/**
 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings,
 * and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 * A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B,
 * with A and B nonempty valid parentheses strings.
 * Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k,
 * where P_i are primitive valid parentheses strings.
 * Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
 *
 * Example 1:
 * Input: "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 *
 * Example 2:
 * Input: "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 *
 * Note:
 * 1. S.length <= 10000
 * 2. S[i] is "(" or ")"
 * 3. S is a valid parentheses string
 */
public class RemoveOutermostParentheses {
    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("(()())(())"));
    }

    /**
     * 括号分为两种，一种是合法子串的最外层括号，不能加到结果中，另一种是其他位置上的括号，要加到结果中。
     * 所以只要区分出这两种情况，就知道当前括号要不要加，区别的方法是使用遍历 count 记录当前需要的右括号个数，
     * 遍历 S，当遇到左括号时，若此时 count > 0，则一定不是合法子串的起始位置，可以加入结果中，同时 count++；
     * 同理，若遇到右括号，若此时 count > 1，则一定不是合法子串的结束位置，可以加入结果中，同时 count--
     */
    public static String removeOuterParentheses(String S) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        char[] array = S.toCharArray();
        for (char cur : array) {
            if (cur == '(' && count++ > 0) {
                builder.append(cur);
            }
            if (cur == ')' && count-- > 1) {
                builder.append(cur);
            }
        }
        return builder.toString();
    }
}
