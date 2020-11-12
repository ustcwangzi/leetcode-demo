package com.wz.string;

/**
 * A string is a valid parentheses string (denoted VPS) if it meets one of the following:
 * 1. It is an empty string "", or a single character not equal to "(" or ")",
 * 2. It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * 3. It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 * 1. depth("") = 0
 * 2. depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
 * 3. depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
 * 4. depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
 * Given a VPS represented as string s, return the nesting depth of s.
 *
 * Example 1:
 * Input: s = "(1+(2*3)+((8)/4))+1"
 * Output: 3
 * Explanation: Digit 8 is inside of 3 nested parentheses in the string.
 *
 * Example 2:
 * Input: s = "(1)+((2))+(((3)))"
 * Output: 3
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
 * 3. It is guaranteed that parentheses expression s is a VPS.
 */
public class MaximumNestingDepthOfTheParentheses {
    public static void main(String[] args) {
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
    }

    /**
     * 最大的括号层数
     */
    public static int maxDepth(String s) {
        char[] array = s.toCharArray();
        int count = 0, result = 0;

        for (char c : array) {
            if (c == '(') {
                count++;
                result = Math.max(count, result);
            } else if (c == ')') {
                count--;
            }
        }
        return result;
    }
}
