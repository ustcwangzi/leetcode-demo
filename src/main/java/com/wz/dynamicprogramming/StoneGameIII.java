package com.wz.dynamicprogramming;

/**
 * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row,
 * and each stone has an associated value which is an integer given in the array stoneValue.
 * Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones
 * from the first remaining stones in the row.
 * The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.
 * The objective of the game is to end with the highest score, and the winner is the player with the highest score
 * and there could be a tie. The game continues until all the stones have been taken.
 * Assume Alice and Bob play optimally.
 * Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.
 *
 * Example 1:
 * Input: values = [1,2,3,7]
 * Output: "Bob"
 * Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
 *
 * Example 2:
 * Input: values = [1,2,3,-9]
 * Output: "Alice"
 * Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative score.
 * If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. The next move Alice will take the pile with value = -9 and lose.
 * If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. The next move Alice will take the pile with value = -9 and also lose.
 * Remember that both play optimally so here Alice will choose the scenario that makes her win.
 *
 * Example 3:
 * Input: values = [1,2,3,6]
 * Output: "Tie"
 * Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
 *
 * Constraints:
 * 1. 1 <= values.length <= 50000
 * 2. -1000 <= values[i] <= 1000
 */
public class StoneGameIII {
    public static void main(String[] args) {
        System.out.println(stoneGameIII(new int[]{1, 2, 3, 7}));
    }

    /**
     * 把数的大小看作收益，自己收益为x，别人收益为y，纯收益 x-y
     * 不同取法都会对后面造成影响，但不管怎么取，都是从前往后，如果只剩1个数，那就不得不取，收益一定，为 stoneValue[n]，哪怕是负的
     * 如果只剩 2 个数，可以取 2 个，收益为 stoneValue[n-1] + stoneValue[n]
     * 也可以取 1 个，收益为 stoneValue[n-1]，则剩下的 1 个给别人拿，别人也有个收益，相减就是纯收益
     * 如果剩下 3 个数，同理可以取 1、2、3 个，但是下一个人剩下 2、1、0 个数的收益是一样的
     * 设 dp[i] 表示剩下的 [i...n] 这些数能够获取的最大收益，当然是从 dp[n] 开始逆推到 dp[1]，只剩一个的时候收益固定
     * 每一次取法都有三种情况
     */
    public static String stoneGameIII(int[] stoneValue) {
        int[] dp = new int[50005];
        int n = stoneValue.length - 1;
        for (int i = n; i >= 0; i--) {
            // 取一个数
            dp[i] = stoneValue[i] - dp[i + 1];
            if (i + 1 <= n) {
                // 取两个数
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            }
            if (i + 2 <= n) {
                // 取三个数
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            }
        }
        if (dp[0] > 0) {
            return "Alice";
        }
        if (dp[0] == 0) {
            return "Tie";
        }
        return "Bob";
    }
}
