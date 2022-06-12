package com.wz.queue;

import java.util.Arrays;

/**
 * You are playing a solitaire game with three piles of stones of sizes a, b, c respectively.
 * Each turn you choose two different non-empty piles, take one stone from each, and add 1 point to your score.
 * The game stops when there are fewer than two non-empty piles (meaning there are no more available moves).
 * Given three integers a, b, c, return the maximum score you can get.
 *
 * Example 1:
 * Input: a = 2, b = 4, c = 6
 * Output: 6
 * Explanation: The starting state is (2, 4, 6). One optimal set of moves is:
 * - Take from 1st and 3rd piles, state is now (1, 4, 5)
 * - Take from 1st and 3rd piles, state is now (0, 4, 4)
 * - Take from 2nd and 3rd piles, state is now (0, 3, 3)
 * - Take from 2nd and 3rd piles, state is now (0, 2, 2)
 * - Take from 2nd and 3rd piles, state is now (0, 1, 1)
 * - Take from 2nd and 3rd piles, state is now (0, 0, 0)
 * There are fewer than two non-empty piles, so the game ends. Total: 6 points.
 *
 * Example 2:
 * Input: a = 4, b = 4, c = 6
 * Output: 7
 * Explanation: The starting state is (4, 4, 6). One optimal set of moves is:
 * - Take from 1st and 2nd piles, state is now (3, 3, 6)
 * - Take from 1st and 3rd piles, state is now (2, 3, 5)
 * - Take from 1st and 3rd piles, state is now (1, 3, 4)
 * - Take from 1st and 3rd piles, state is now (0, 3, 3)
 * - Take from 2nd and 3rd piles, state is now (0, 2, 2)
 * - Take from 2nd and 3rd piles, state is now (0, 1, 1)
 * - Take from 2nd and 3rd piles, state is now (0, 0, 0)
 * There are fewer than two non-empty piles, so the game ends. Total: 7 points.
 *
 * Constraints:
 * 1 <= a, b, c <= 10^5
 */
public class MaximumScoreFromRemovingStones {
    public static void main(String[] args) {
        System.out.println(maximumScore(2, 4, 6));
        System.out.println(maximumScore(4, 4, 6));
    }

    /**
     * 最大的个数 >= 其余两堆之和，只能把小的两堆拿完
     * 否则，只能剩下 0 个 或者 1 个石子
     */
    public static int maximumScore(int a, int b, int c) {
        int[] array = new int[]{a, b, c};
        Arrays.parallelSort(array);
        if (array[0] + array[1] <= array[2]) {
            return array[0] + array[1];
        }
        return (array[0] + array[1] + array[2]) / 2;
    }
}
