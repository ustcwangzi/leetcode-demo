package com.wz.math;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 *
 * Example 1:
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 *
 * Example 2:
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 *
 * Note:
 * 1 <= N <= 1000
 */
public class DivisorGame {
    public static void main(String[] args) {
        System.out.println(divisorGame(2));
        System.out.println(divisorGame(3));
    }

    /**
     * N=1，0 < x < 1且1％x == 0，没有符合的数，Alice输。
     * N=2，0 < x < 2且2％x == 0，Alice取1，N变成1，轮到Bob，Bob无法选择合适的数，Alice赢。
     * N=3，0 < x < 3且3％x == 0，Alice取1，N变成2，轮到Bob，Bob选1，N变成1，轮到Alice再选，没有符合的数，Alice输。
     * N=4，0 < x < 4且4％x == 0，Alice取1，N变成3，轮到Bob，Bob选1，N变成2，轮到Alice再选1，N变成1，再轮到Bob选，没有符合的数，Alice赢。
     * N=5，0 < x < 5且5％x == 0，Alice取1，N变成4，轮到Bob，Bob选1，N变成3，再轮到Alice选，和前面N等于3结果一样，Alice输。
     * N=6，0 < x < 6且6％x == 0，Alice取1，N变成5，轮到Bob，Bob选1，N变成4，再轮到Alice选，和前面N等于4结果一样，Alice赢。
     * 从上面依次计算的例子来看，当N为奇数的时候，谁先开始，谁就输，因为对方肯定会让你继续变成奇数，直到N变成1。
     * 当N为偶数的时候，谁先开始，谁就赢，第一步取1，将N变成奇数，对方只能继续取1或者其他奇数，奇数减去奇数变为偶数，开始的那一方再取1，直到N变成1。
     */
    public static boolean divisorGame(int N) {
        return N % 2 == 0;
    }
}
