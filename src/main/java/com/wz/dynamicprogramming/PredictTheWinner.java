package com.wz.dynamicprogramming;

/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either
 * end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number,
 * that number will not be available for the next player. This continues until all the scores have been chosen.
 * The player with the maximum score wins.
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 *
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 *
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 *
 *
 * Constraints:
 * 1. 1 <= length of the array <= 20.
 * 2. Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * 3. If the scores of both players are equal, then player 1 is still the winner.
 */
public class PredictTheWinner {
    public static void main(String[] args) {
        System.out.println(predictTheWinner(new int[]{1, 5, 2}));
    }

    /**
     * 把问题转换为两个选手所拿元素的差值，若差值大于等于0，则第一个选手获胜
     * 假设当前可选范围为 start ～ end，则递归表达式为
     * max{nums[start] - helper(start+1, end), nums[end] - helper(start, end-1)}
     * 同时用 dp 数组保存中间结果，提高运算效率
     */
    public static boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        return helper(nums, 0, n - 1, new Integer[n][n]) >= 0;
    }

    private static int helper(int[] nums, int start, int end, Integer[][] dp) {
        if (dp[start][end] != null) {
            return dp[start][end];
        }
        if (start == end) {
            return nums[start];
        }
        return Math.max(nums[start] - helper(nums, start + 1, end, dp), nums[end] - helper(nums, start, end - 1, dp));
    }
}
