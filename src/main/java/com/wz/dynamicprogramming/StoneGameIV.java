package com.wz.dynamicprogramming;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
 *
 * Also, if a player cannot make a move, he/she loses the game.
 *
 * Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.
 *
 * Example 1:
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 *
 * Example 2:
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 *
 * Example 3:
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 *
 * Constraints:
 * 1 <= n <= 10^5
 */
public class StoneGameIV {
    public static void main(String[] args) {
        System.out.println(winnerSquareGame(1));
        System.out.println(winnerSquareGame(2));
        System.out.println(winnerSquareGame(4));
    }

    /**
     * dp[i] 表示总数为 i 时，Alice 能否获胜，若取走的平方数为 k，那么只要 dp[i-k] 为 false，Alice 就能获胜
     * 因此可以从 1～n 遍历 i，然后从 1～j*j 遍历 k，若 dp[i - j*j] 为 false，则 dp[i] = true，最后返回 dp[n]
     */
    public static boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
