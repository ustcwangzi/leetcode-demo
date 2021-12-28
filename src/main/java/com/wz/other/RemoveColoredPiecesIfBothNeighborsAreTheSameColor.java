package com.wz.other;

/**
 * There are n pieces arranged in a line, and each piece is colored either by 'A' or by 'B'. You are given a string colors of length n where colors[i] is the color of the ith piece.
 * Alice and Bob are playing a game where they take alternating turns removing pieces from the line. In this game, Alice moves first.
 * 1. Alice is only allowed to remove a piece colored 'A' if both its neighbors are also colored 'A'. She is not allowed to remove pieces that are colored 'B'.
 * 2. Bob is only allowed to remove a piece colored 'B' if both its neighbors are also colored 'B'. He is not allowed to remove pieces that are colored 'A'.
 * 3. Alice and Bob cannot remove pieces from the edge of the line.
 * 4. If a player cannot make a move on their turn, that player loses and the other player wins.
 * Assuming Alice and Bob play optimally, return true if Alice wins, or return false if Bob wins.
 *
 * Example 1:
 * Input: colors = "AAABABB"
 * Output: true
 * Explanation:
 * AAABABB -> AABABB
 * Alice moves first.
 * She removes the second 'A' from the left since that is the only 'A' whose neighbors are both 'A'.
 * Now it's Bob's turn.
 * Bob cannot make a move on his turn since there are no 'B's whose neighbors are both 'B'.
 * Thus, Alice wins, so return true.
 *
 * Example 2:
 * Input: colors = "AA"
 * Output: false
 * Explanation:
 * Alice has her turn first.
 * There are only two 'A's and both are on the edge of the line, so she cannot move on her turn.
 * Thus, Bob wins, so return false.
 *
 * Example 3:
 * Input: colors = "ABBBBBBBAAA"
 * Output: false
 * Explanation:
 * ABBBBBBBAAA -> ABBBBBBBAA
 * Alice moves first.
 * Her only option is to remove the second to last 'A' from the right.
 * ABBBBBBBAA -> ABBBBBBAA
 * Next is Bob's turn.
 * He has many options for which 'B' piece to remove. He can pick any.
 * On Alice's second turn, she has no more pieces that she can remove.
 * Thus, Bob wins, so return false.
 *
 * Constraints:
 * 1. 1 <= colors.length <= 10^5
 * 2. colors consists of only the letters 'A' and 'B'
 */
public class RemoveColoredPiecesIfBothNeighborsAreTheSameColor {
    public static void main(String[] args) {
        System.out.println(winnerOfGame("AAABABB"));
        System.out.println(winnerOfGame("ABBBBBBBAAA"));
    }

    /**
     * Alice 只能移除连续的三个 A，Bob 只能移除连续的三个 B，使用 countA、countB 分别记录当前连续字符个数
     * 使用 moveA、moveB 分别记录 Alice 和 Bob 操作次数，然后遍历字符串
     * 若当前字符为 A，则将 countB 置为 0，countA++，同时若 countA 大于 2 则可以移除一个 A，moveA++，反之类似
     * 最后，判断 countA 是否大于 countB
     */
    public static boolean winnerOfGame(String colors) {
        int countA = 0, moveA = 0, countB = 0, moveB = 0;
        for (int i = 0; i < colors.length(); i++) {
            if (colors.charAt(i) == 'A') {
                countB = 0;
                if (++countA > 2) {
                    moveA++;
                }
            } else {
                countA = 0;
                if (++countB > 2) {
                    moveB++;
                }
            }
        }
        return moveA > moveB;
    }
}
