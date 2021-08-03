package com.wz.other;

/**
 * You are playing the following Nim Game with your friend:
 * 1. Initially, there is a heap of stones on the table.
 * 2. You and your friend will alternate taking turns, and you go first.
 * 3. On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
 * 4. The one who removes the last stone is the winner.
 * Given n, the number of stones in the heap, return true if you can win the game assuming both you and your friend play optimally, otherwise return false.
 *
 * Example 1:
 * Input: n = 4
 * Output: false
 * Explanation: These are the possible outcomes:
 * 1. You remove 1 stone. Your friend removes 3 stones, including the last stone. Your friend wins.
 * 2. You remove 2 stones. Your friend removes 2 stones, including the last stone. Your friend wins.
 * 3. You remove 3 stones. Your friend removes the last stone. Your friend wins.
 * In all outcomes, your friend wins.
 *
 * Example 2:
 * Input: n = 2
 * Output: true
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */
public class NimGame {
    public static void main(String[] args) {
        System.out.println(canWinNim(4));
        System.out.println(canWinNim(2));
    }

    /**
     * 如果 n 是4的倍数，后手每次拿走 4-x 即可，x为对手上一次拿走的数量，此时后手必赢
     * 如果 n 不是4的倍数，先手第一次拿走 n%4，造成对手为4的倍数，此时先手必赢
     */
    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
