package com.wz.backtracking;

/**
 * You are given an integer n, the number of teams in a tournament that has strange rules:
 * 1. If the current number of teams is even, each team gets paired with another team.
 *    A total of n / 2 matches are played, and n / 2 teams advance to the next round.
 * 2. If the current number of teams is odd, one team randomly advances in the tournament, and the rest gets paired.
 *    A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1 teams advance to the next round.
 * Return the number of matches played in the tournament until a winner is decided.
 *
 * Example 1:
 * Input: n = 7
 * Output: 6
 * Explanation: Details of the tournament:
 * - 1st Round: Teams = 7, Matches = 3, and 4 teams advance.
 * - 2nd Round: Teams = 4, Matches = 2, and 2 teams advance.
 * - 3rd Round: Teams = 2, Matches = 1, and 1 team is declared the winner.
 * Total number of matches = 3 + 2 + 1 = 6.
 *
 * Example 2:
 * Input: n = 14
 * Output: 13
 * Explanation: Details of the tournament:
 * - 1st Round: Teams = 14, Matches = 7, and 7 teams advance.
 * - 2nd Round: Teams = 7, Matches = 3, and 4 teams advance.
 * - 3rd Round: Teams = 4, Matches = 2, and 2 teams advance.
 * - 4th Round: Teams = 2, Matches = 1, and 1 team is declared the winner.
 * Total number of matches = 7 + 3 + 2 + 1 = 13.
 *
 * Constraints:
 * 1 <= n <= 200
 */
public class CountOfMatchesInTournament {
    public static void main(String[] args) {
        System.out.println(numberOfMatches1(7));
        System.out.println(numberOfMatches2(7));
    }

    /**
     * 方法一：按照规则直接求解
     */
    public static int numberOfMatches1(int n) {
        int result = 0;
        while (n > 1) {
            if ((n & 1) == 1) {
                // 奇数，加上 (n-1)/2 次配对次数，剩下 (n-1)/2+1 队伍
                result += (n - 1) / 2;
                n = (n - 1) / 2 + 1;
            } else {
                //偶数,加上n/2次配对次数,剩下n/2支队伍
                result += n / 2;
                n /= 2;
            }
        }
        return result;
    }

    /**
     * 方法二：每次比赛就会淘汰一个队，一开始 n 个队，最后 1 个队，所以答案就是 n − 1
     */
    public static int numberOfMatches2(int n) {
        return n - 1;
    }
}
