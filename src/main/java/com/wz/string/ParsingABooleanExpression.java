package com.wz.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Return the result of evaluating a given boolean expression, represented as a string.
 * An expression can either be:
 * 1. "t", evaluating to True;
 * 2. "f", evaluating to False;
 * 3. "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * 4. "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * 5. "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 *
 * Example 1:
 * Input: expression = "!(f)"
 * Output: true
 *
 * Example 2:
 * Input: expression = "|(f,t)"
 * Output: true
 *
 * Example 3:
 * Input: expression = "&(t,f)"
 * Output: false
 *
 * Example 4:
 * Input: expression = "|(&(t,f,t),!(t))"
 * Output: false
 *
 * Constraints:
 * 1. 1 <= expression.length <= 20000
 * 2. expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
 * 3. expression is a valid expression representing a boolean, as given in the description.
 */
public class ParsingABooleanExpression {
    public static void main(String[] args) {
        System.out.println(parseBoolExpr("|(&(t,f,t),!(t))"));
    }

    public static boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ',') {
                continue;
            }
            if (expression.charAt(i) == ')') {
                // start removing the things from the stack till you see a operator
                List<Character> list = new ArrayList<>();
                while (stack.peek() != '(') {
                    list.add(stack.pop());
                }
                // to remove the starting bracket
                stack.pop();
                // called the function with the function
                // and the operands
                boolean valAdd = helper(stack.pop(), list);
                if (valAdd) {
                    stack.push('t');
                } else {
                    stack.push('f');
                }
            } else {
                stack.push(expression.charAt(i));
            }
        }
        return stack.pop() == 't';
    }

    public static boolean helper(char op, List<Character> list) {
        boolean result = list.get(0) == 't';
        for (char cur : list) {
            if (op == '&') {
                result = result && cur == 't';
            } else if (op == '|') {
                result = result || cur == 't';
            } else {
                //for the not case
                result = !result;
            }
        }
        return result;
    }
}
