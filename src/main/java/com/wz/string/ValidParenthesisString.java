package com.wz.string;

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
     * low 表示当*代表左括号时左括号剩余个数，high 表示当*代表右括号时左括号剩余个数。
     * 那么当 high 小于0时，说明就算把星号都当作左括号了，还是不够抵消右括号，返回 false
     * 而当 low 大于0时，说明左括号的个数太多了，没有足够多的右括号来抵消，返回 false
     * 遍历字符串，当遇到左括号时，low 和 high 都自增1；
     * 当遇到右括号时，只有 low 大于0时，low 才减1，保证了low不会小于0，而 high 直接自减1；
     * 当遇到星号时，只有当 low 大于0时，low 才减1，保证了low不会小于0，而 high 直接自增1，因为 high 把星号当作左括号
     * 若此时 high 小于0，说明右括号太多，返回 false。当循环退出后，看 low 是否为0
     */
    public static boolean checkValidString(String s) {
        if (s.length() == 0 || s.equals("*")) {
            return true;
        }

        int low = 0, high = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }
}
