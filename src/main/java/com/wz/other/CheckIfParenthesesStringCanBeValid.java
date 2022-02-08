package com.wz.other;

/**
 * A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
 * 1. It is ().
 * 2. It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
 * 3. It can be written as (A), where A is a valid parentheses string.
 * You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,
 * 1. If locked[i] is '1', you cannot change s[i].
 * 2. But if locked[i] is '0', you can change s[i] to either '(' or ')'.
 * Return true if you can make s a valid parentheses string. Otherwise, return false.
 *
 * Example 1:
 * Input: s = "))()))", locked = "010100"
 * Output: true
 * Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
 * We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
 *
 * Example 2:
 * Input: s = "()()", locked = "0000"
 * Output: true
 * Explanation: We do not need to make any changes because s is already valid.
 *
 * Example 3:
 * Input: s = ")", locked = "0"
 * Output: false
 * Explanation: locked permits us to change s[0].
 * Changing s[0] to either '(' or ')' will not make s valid.
 *
 * Constraints:
 * 1. n == s.length == locked.length
 * 2. 1 <= n <= 10^5
 * 3. s[i] is either '(' or ')'.
 * 4. locked[i] is either '0' or '1'.
 */
public class CheckIfParenthesesStringCanBeValid {
    public static void main(String[] args) {
        System.out.println(canBeValid("))()))", "010100"));
        System.out.println(canBeValid("()()", "0000"));
    }

    /**
     * 和 {@link ValidParenthesisString} 类似，locked[i] == 0 时，等价于 *
     */
    public static boolean canBeValid(String s, String locked) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }

        int min = 0, max = 0;
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '0') {
                min--;
                max++;
            } else {
                if (s.charAt(i) == '(') {
                    min++;
                    max++;
                } else {
                    min--;
                    max--;
                }
            }
            if (max < 0) {
                return false;
            }
            min = Math.max(min, 0);
        }
        return min == 0;
    }
}
