package com.wz.other;

/**
 * Given a string containing only three types of characters: '(', ')' and '*',
 * write a function to check whether this string is valid. We define the validity of a string by these rules:
 * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * 4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * 5. An empty string is also valid.
 *
 * Example 1:
 * Input: "(*)"
 * Output: True
 *
 * Example 3:
 * Input: "(*))"
 * Output: True
 */
public class ValidParenthesisString {
    public static void main(String[] args) {
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("(*))"));
    }

    /**
     * 左括号个数范围为 [min, max]，遍历字符串
     * 若当前为左括号，min++、max++
     * 若当前为右括号，min--、max--
     * 若当前为 *，分三种情况：
     * 1. * 为左括号，max++
     * 2. * 为右括号，min--
     * 3. * 为''，min 和 max 都不用变化
     * 而当 max<0 时，说明左括号的个数太少了，返回 false，如 ())(
     * 同时需要注意 min 最小为 0，不可能为负数
     * 最后，若 min == 0，说明能够找到合法的字符串
     */
    public static boolean checkValidString(String s) {
        if (s.length() == 0 || s.equals("*")) {
            return true;
        }

        int min = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                min++;
                max++;
            } else if (s.charAt(i) == ')') {
                min--;
                max--;
            } else {
                min--;
                max++;
            }
            if (max < 0) {
                return false;
            }
            min = Math.max(min, 0);
        }
        return min == 0;
    }
}
