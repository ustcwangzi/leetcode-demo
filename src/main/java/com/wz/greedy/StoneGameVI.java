package com.wz.greedy;

import java.util.Arrays;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 * There are n stones in a pile. On each player's turn, they can remove a stone from the pile and receive points based on the stone's value. Alice and Bob may value the stones differently.
 * You are given two integer arrays of length n, aliceValues and bobValues. Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value the ith stone.
 * The winner is the person with the most points after all the stones are chosen. If both players have the same amount of points, the game results in a draw. Both players will play optimally. Both players know the other's values.
 * Determine the result of the game, and:
 * 1. If Alice wins, return 1.
 * 2. If Bob wins, return -1.
 * 3. If the game results in a draw, return 0.
 *
 *
 * Example 1:
 * Input: aliceValues = [1,3], bobValues = [2,1]
 * Output: 1
 * Explanation:
 * If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
 * Bob can only choose stone 0, and will only receive 2 points.
 * Alice wins.
 *
 * Example 2:
 * Input: aliceValues = [1,2], bobValues = [3,1]
 * Output: 0
 * Explanation:
 * If Alice takes stone 0, and Bob takes stone 1, they will both have 1 point.
 *
 * Example 3:
 * Input: aliceValues = [2,4,3], bobValues = [1,6,7]
 * Output: -1
 * Explanation:
 * Regardless of how Alice plays, Bob will be able to have more points than Alice.
 * For example, if Alice takes stone 1, Bob can take stone 2, and Alice takes stone 0, Alice will have 6 points to Bob's 7.
 * Bob wins.
 *
 * Constraints:
 * 1. n == aliceValues.length == bobValues.length
 * 2. 1 <= n <= 10^5
 * 3. 1 <= aliceValues[i], bobValues[i] <= 100
 */
public class StoneGameVI {
    public static void main(String[] args) {
        System.out.println(stoneGameVI(new int[]{1, 3}, new int[]{2, 1}));
        System.out.println(stoneGameVI(new int[]{1, 2}, new int[]{3, 1}));
        System.out.println(stoneGameVI(new int[]{2, 4, 3}, new int[]{1, 6, 7}));
    }

    /**
     * 假设 A 取石子的方案为 point[0...n-1]，每个元素值为 0 或 1，1 代表取该下标的石子，否则不取，那么 A 与 B 的分差为
     * point[i] * A[i] - (1-point[i]) * B[i] = point[i] * (A[i] + B[i]) - B[i]
     * 如果 A 要最大化分差，就需要取 A[i] + B[i] 尽可能大的石子，而 B 也是如此。
     * 因此可以模拟该过程，最后看谁的得分高。
     */
    public static int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = aliceValues[i];
            points[i][1] = bobValues[i];
        }
        Arrays.parallelSort(points, (o1, o2) -> -Integer.compare(o1[0] + o1[1], o2[0] + o2[1]));

        int aScore = 0, bScore = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                aScore += points[i][0];
            } else {
                bScore += points[i][1];
            }
        }
        return Integer.compare(aScore, bScore);
    }
}
