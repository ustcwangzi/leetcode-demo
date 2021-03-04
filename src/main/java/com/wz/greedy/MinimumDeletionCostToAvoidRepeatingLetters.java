package com.wz.greedy;

/**
 * Given a string s and an array of integers cost where cost[i] is the cost of deleting the ith character in s.
 * Return the minimum cost of deletions such that there are no two identical letters next to each other.
 * Notice that you will delete the chosen characters at the same time, in other words,
 * after deleting a character, the costs of deleting other characters will not change.
 *
 * Example 1:
 * Input: s = "abaac", cost = [1,2,3,4,5]
 * Output: 3
 * Explanation: Delete the letter "a" with cost 3 to get "abac" (String without two identical letters next to each other).
 *
 * Example 2:
 * Input: s = "aabaa", cost = [1,2,3,4,1]
 * Output: 2
 * Explanation: Delete the first and the last character, getting the string ("aba").
 *
 * Constraints:
 * 1. s.length == cost.length
 * 2. 1 <= s.length, cost.length <= 10^5
 * 3. 1 <= cost[i] <= 10^4
 * 4. s contains only lowercase English letters.
 */
public class MinimumDeletionCostToAvoidRepeatingLetters {
    public static void main(String[] args) {
        System.out.println(minCost("abaac", new int[]{1, 2, 3, 4, 5}));
        System.out.println(minCost("aabaa", new int[]{1, 2, 3, 4, 1}));
    }

    /**
     * 遍历数组，如果相邻元素相同，则移除两者直接 cost 较小的，同时更新移除之后的 cost
     */
    public static int minCost(String s, int[] cost) {
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                result += Math.min(cost[i - 1], cost[i]);
                if (cost[i - 1] > cost[i]) {
                    cost[i] = cost[i - 1];
                }
            }
        }
        return result;
    }
}
