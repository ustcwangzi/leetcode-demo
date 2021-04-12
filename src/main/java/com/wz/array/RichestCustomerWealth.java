package com.wz.array;

/**
 * You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the i customer has in the j bank.
 * Return the wealth that the richest customer has. A customer's wealth is the amount of money they have in all their bank accounts.
 * The richest customer is the customer that has the maximum wealth.
 *
 * Example 1:
 * Input: accounts = [[1,2,3],[3,2,1]]
 * Output: 6
 * Explanation:
 * 1st customer has wealth = 1 + 2 + 3 = 6
 * 2nd customer has wealth = 3 + 2 + 1 = 6
 * Both customers are considered the richest with a wealth of 6 each, so return 6.
 *
 * Example 2:
 * Input: accounts = [[1,5],[7,3],[3,5]]
 * Output: 10
 * Explanation:
 * 1st customer has wealth = 6
 * 2nd customer has wealth = 10
 * 3rd customer has wealth = 8
 * The 2nd customer is the richest with a wealth of 10.
 *
 * Constraints:
 * 1. m == accounts.length
 * 2. n == accounts[i].length
 * 3. 1 <= m, n <= 50
 * 4. 1 <= accounts[i][j] <= 100
 */
public class RichestCustomerWealth {
    public static void main(String[] args) {
        System.out.println(maximumWealth(new int[][]{{1, 5}, {7, 3}, {3, 5}}));
    }

    /**
     * 计算每一行的累加和 cur，统计最大值
     */
    public static int maximumWealth(int[][] accounts) {
        int result = 0;
        for (int[] account : accounts) {
            int cur = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                cur += account[j];
            }
            result = Math.max(result, cur);
        }
        return result;
    }
}
