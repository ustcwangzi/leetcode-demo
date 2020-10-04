package com.wz.string;

import java.util.LinkedList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(1));
    }

    /**
     * 回溯法
     * 右括号个数不能大于左括号个数
     * left 和 right 分别表示剩余左右括号的个数，若 left 和 right 都为0，则说明此时生成的字符串已有n个左括号和n个右括号，存入结果中
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        generate(n, n, "", result);
        return result;
    }

    private static void generate(int left, int right, String str, List<String> result) {
        if (left < 0 || right < 0 || left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(str);
            return;
        }

        generate(left - 1, right, str + "(", result);
        generate(left, right - 1, str + ")", result);
    }
}
