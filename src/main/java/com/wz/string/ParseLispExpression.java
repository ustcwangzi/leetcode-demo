package com.wz.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * You are given a string expression representing a Lisp-like expression to return the integer value of.
 * The syntax for these expressions is given as follows.
 * 1. An expression is either an integer, a let-expression, an add-expression, a mult-expression, or an assigned variable.
 *    Expressions always evaluate to a single integer.
 * 2. An integer could be positive or negative.
 * 3. A let-expression takes the form (let v1 e1 v2 e2 ... vn en expr), where let is always the string "let",
 *    then there are 1 or more pairs of alternating variables and expressions,
 *    meaning that the first variable v1 is assigned the value of the expression e1,
 *    the second variable v2 is assigned the value of the expression e2, and so on sequentially;
 *    and then the value of this let-expression is the value of the expression expr.
 * 4. An add-expression takes the form (add e1 e2) where add is always the string "add", there are always two expressions e1, e2,
 *    and this expression evaluates to the addition of the evaluation of e1 and the evaluation of e2.
 * 5. A mult-expression takes the form (mult e1 e2) where mult is always the string "mult", there are always two expressions e1, e2,
 *    and this expression evaluates to the multiplication of the evaluation of e1 and the evaluation of e2.
 * 6. For the purposes of this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter,
 *    then zero or more lowercase letters or digits. Additionally for your convenience, the names "add", "let",
 *    or "mult" are protected and will never be used as variable names.
 * 7. Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation,
 *    the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially.
 *    It is guaranteed that every expression is legal. Please see the examples for more details on scope.
 *
 * Evaluation Examples:
 * Input: (add 1 2)
 * Output: 3
 *
 * Input: (mult 3 (add 2 3))
 * Output: 15
 *
 * Input: (let x 2 (mult x 5))
 * Output: 10
 */
public class ParseLispExpression {
    public static void main(String[] args) {
        System.out.println(evaluate("(let x 2 (mult x 5))"));
    }

    public static int evaluate(String expression) {
        return evaluate(expression, new HashMap<>());
    }

    private static int evaluate(String expression, Map<String, Integer> map) {
        if (expression.charAt(0) != '(') {
            if (Character.isDigit(expression.charAt(0)) || expression.charAt(0) == '-') {
                return Integer.parseInt(expression);
            }
            return map.get(expression);
        }

        List<String> list = parse(expression);
        // 外层的变量值不会随着里层的变量值改变
        Map<String, Integer> newMap = new HashMap<>(map);
        if (expression.charAt(1) == 'm') {
            return evaluate(list.get(0), newMap) * evaluate(list.get(1), newMap);
        } else if (expression.charAt(1) == 'a') {
            return evaluate(list.get(0), newMap) + evaluate(list.get(1), newMap);
        } else {
            for (int i = 0; i < list.size() - 1; i += 2) {
                newMap.put(list.get(i), evaluate(list.get(i + 1), newMap));
            }
            return evaluate(list.get(list.size() - 1), newMap);
        }
    }

    private static List<String> parse(String expression) {
        List<String> res = new LinkedList<>();
        for (int i = expression.indexOf(' ') + 1, p = 0, last = i; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ')') p--;
            if (c == '(') p++;
            if (p == 0 && c == ' ' || i == expression.length() - 1) {
                res.add(expression.substring(last, i));
                last = i + 1;
            }
        }
        return res;
    }
}
