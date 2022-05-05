package com.wz.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 * Return all the possible results. You may return the answer in any order.
 *
 * Example 1:
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 *
 * Example 2:
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 *
 * Example 3:
 * Input: s = ")("
 * Output: [""]
 *
 * Constraints:
 * 1. 1 <= s.length <= 25
 * 2. s consists of lowercase English letters and parentheses '(' and ')'.
 * 3. There will be at most 20 parentheses in s.
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses invalidParentheses = new RemoveInvalidParentheses();
        System.out.println(invalidParentheses.removeInvalidParentheses("()())()"));

        invalidParentheses = new RemoveInvalidParentheses();
        System.out.println(invalidParentheses.removeInvalidParentheses("(a)())()"));

        invalidParentheses = new RemoveInvalidParentheses();
        System.out.println(invalidParentheses.removeInvalidParentheses(")("));
    }

    private Set<String> result = new HashSet<>();
    private int minRemoveCount = Integer.MAX_VALUE;

    /**
     * DFS
     * 记录当前左右括号的数量 left、right，以及当前已删除括号的数量 count
     * 若当前字符不是括号，则不影响要删除的个数，直接加入 tmp 中、遍历下一个字符
     * 若当前字符是括号，则有两种选择：
     * 1、删除当前括号，count+1，继续遍历下一个字符
     * 2、不删除当前括号
     *    若当前为左括号，则加入 tmp 中、遍历下一个字符，同时左括号数量加一
     *    若当前为右括号，则需要满足 left > right 才能将其加入 tmp 中，不然会导致最终的 tmp 不满足条件
     * 遍历到结束位置时，若 left == right，说明满足条件，但同时需要检查 count 是否为最小的 minRemoveCount
     * 因为题目要求删除最少的括号，若 count 不是最小，则需要将之前的结果清空，并更新 minRemoveCount
     */
    public List<String> removeInvalidParentheses(String s) {
        dfs(s, 0, "", 0, 0, 0);
        return new ArrayList<>(result);
    }

    private void dfs(String s, int index, String tmp, int left, int right, int count) {
        if (index == s.length() && left == right) {
            if (count <= minRemoveCount) {
                if (count < minRemoveCount) {
                    result = new HashSet<>();
                }
                result.add(tmp);
                minRemoveCount = count;
            }
            return;
        }
        if (index >= s.length()) {
            return;
        }

        char cur = s.charAt(index);
        if (cur != '(' && cur != ')') {
            // 不影响结果
            dfs(s, index + 1, tmp + cur, left, right, count);
        } else {
            // 删除当前字符
            dfs(s, index + 1, tmp, left, right, count + 1);

            // 不删除当前字符
            if (cur == '(') {
                dfs(s, index + 1, tmp + cur, left + 1, right, count);
            } else if (left > right) {
                dfs(s, index + 1, tmp + cur, left, right + 1, count);
            }
        }
    }
}
