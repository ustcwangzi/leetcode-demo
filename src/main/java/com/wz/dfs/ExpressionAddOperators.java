package com.wz.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the
 * binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * Example 1:
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 *
 * Example 2:
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 *
 * Example 3:
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 * Constraints:
 * 1. 1 <= num.length <= 10
 * 2. num consists of only digits.
 * 3. -2^31 <= target <= 2^31 - 1
 */
public class ExpressionAddOperators {
    public static void main(String[] args) {
        System.out.println(addOperators("123", 6));
        System.out.println(addOperators("232", 8));
    }

    /**
     * DFS
     * 使用 DFS 遍历每一种情况，注意 * 操作时，需要减去 pre，因为破坏了前面已经计算的结果
     */
    public static List<String> addOperators(String num, int target) {
        List<String> result = new LinkedList<>();
        dfs(num, target, 0, 0, 0, "", result);
        return result;
    }

    private static void dfs(String num, int target, int index, long cur, long pre, String tmp, List<String> result) {
        if (index == num.length()) {
            if (cur == target) {
                result.add(tmp);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            // 不能以 0 开始
            if (i != index && num.charAt(index) == '0') {
                break;
            }

            long value = Long.parseLong(num.substring(index, i + 1));
            if (index == 0) {
                dfs(num, target, i + 1, value, value, tmp + value, result);
            } else {
                dfs(num, target, i + 1, cur + value, value, tmp + "+" + value, result);
                dfs(num, target, i + 1, cur - value, -value, tmp + "-" + value, result);
                dfs(num, target, i + 1, cur + pre * value - pre, pre * value, tmp + "*" + value, result);
            }
        }
    }
}
