package com.wz.dynamicprogramming;

/**
 * Alice plays the following game, loosely based on the card game "21".
 * Alice starts with 0 points, and draws numbers while she has less than K points.
 * During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.
 * Each draw is independent and the outcomes have equal probabilities.
 * Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
 *
 * Example 1:
 * Input: N = 10, K = 1, W = 10
 * Output: 1.00000
 * Explanation:  Alice gets a single card, then stops.
 *
 * Example 2:
 * Input: N = 6, K = 1, W = 10
 * Output: 0.60000
 * Explanation:  Alice gets a single card, then stops.
 * In 6 out of W = 10 possibilities, she is at or below N = 6 points.
 *
 * Example 3:
 * Input: N = 21, K = 17, W = 10
 * Output: 0.73278
 *
 * Note:
 * 1. 0 <= K <= N <= 10000
 * 2. 1 <= W <= 10000
 * 3. Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 * 4. The judging time limit has been reduced for this question.
 */
public class New21Game {
    public static void main(String[] args) {
        System.out.println(new21Game(21, 17, 10));
    }

    public static double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        double[] probabilities = new double[K + 1];
        probabilities[0] = 1.0;
        probabilities[1] = 1.0 / W;
        double magic_w = (W + 1.0) / (double) W;
        for (int i = 2; i < K; i++) {
            // derived from recurrence relations,
            // probabilities[i] denominates chance of Alice having i number after arbitrary many draws
            probabilities[i] = magic_w * probabilities[i - 1];
            if (i - W - 1 >= 0) {
                probabilities[i] -= (1.0 / W) * probabilities[i - W - 1];
            }
        }

        // We are summing probabilities of Alice having number bigger than N after her final draw
        // Observe valid numbers (/positions) Alice can have before her final draw are numbers smaller than K
        double inverseAns = 0.0;
        for (int i = Math.max(K - W + 1, 0); i < K; i++) {
            // We can "easily" count, for each number i < K numbers j Alice can draw to cross N
            int countOfDrawsCrossingN = W - N + i;
            if (countOfDrawsCrossingN > 0) {
                inverseAns += probabilities[i] * countOfDrawsCrossingN * 1.0 / W;
            }
        }

        return 1.0 - inverseAns;
    }
}
