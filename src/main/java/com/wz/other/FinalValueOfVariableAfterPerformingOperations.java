package com.wz.other;

/**
 * There is a programming language with only four operations and one variable X:
 * 1. ++X and X++ increments the value of the variable X by 1.
 * 2. --X and X-- decrements the value of the variable X by 1.
 * Initially, the value of X is 0.
 * Given an array of strings operations containing a list of operations, return the final value of X after performing all the operations.
 *
 * Example 1:
 * Input: operations = ["--X","X++","X++"]
 * Output: 1
 * Explanation: The operations are performed as follows:
 * Initially, X = 0.
 * --X: X is decremented by 1, X =  0 - 1 = -1.
 * X++: X is incremented by 1, X = -1 + 1 =  0.
 * X++: X is incremented by 1, X =  0 + 1 =  1.
 *
 * Example 2:
 * Input: operations = ["++X","++X","X++"]
 * Output: 3
 * Explanation: The operations are performed as follows:
 * Initially, X = 0.
 * ++X: X is incremented by 1, X = 0 + 1 = 1.
 * ++X: X is incremented by 1, X = 1 + 1 = 2.
 * X++: X is incremented by 1, X = 2 + 1 = 3.
 *
 * Constraints:
 * 1. 1 <= operations.length <= 100
 * 2. operations[i] will be either "++X", "X++", "--X", or "X--".
 */
public class FinalValueOfVariableAfterPerformingOperations {
    public static void main(String[] args) {
        System.out.println(finalValueAfterOperations(new String[]{"--X", "X++", "X++"}));
    }

    /**
     * 直接遍历
     */
    public static int finalValueAfterOperations(String[] operations) {
        int count = 0;
        for (String cur : operations) {
            if ("++X".equals(cur) || "X++".equals(cur)) {
                count++;
            } else {
                count--;
            }
        }
        return count;
    }
}
