package com.wz.backtracking;

/**
 * Additive number is a string whose digits can form additive sequence.
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 * Example 1:
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 * Example 2:
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199.
 *              1 + 99 = 100, 99 + 100 = 199
 *
 * Constraints:
 * 1. num consists only of digits '0'-'9'.
 * 2. 1 <= num.length <= 35
 * Follow up:
 * How would you handle overflow for very large input integers?
 */
public class AdditiveNumber {
    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("112358"));
        System.out.println(isAdditiveNumber("199100199"));
    }

    /**
     * DFS，与 {@link com.wz.string.SplitArrayIntoFibonacciSequence} 类似，只是本题不需要返回组合方式
     */
    public static boolean isAdditiveNumber(String num) {
        return dfs(num, 0, -1, -1, 0);
    }

    private static boolean dfs(String num, int index, long first, long second, int size) {
        // 最少需要 3 个数字
        if (index >= num.length()) {
            return size >= 3;
        }
        if (num.charAt(index) == '0') {
            if (size < 2 || first + second == 0) {
                return dfs(num, index + 1, second, 0, size + 1);
            }
            return false;
        }

        long sum = 0;
        // 对于每个 sum，检查是不是等于 first 和 second 之和
        for (int i = index; i < num.length(); i++) {
            sum = sum * 10 + (num.charAt(i) - '0');
            // 溢出
            if (sum < 0) {
                break;
            }
            if (size < 2 || first + second == sum) {
                if (dfs(num, i + 1, second, sum, size + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
