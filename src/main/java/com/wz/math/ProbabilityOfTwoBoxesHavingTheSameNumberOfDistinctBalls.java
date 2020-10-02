package com.wz.math;

/**
 * Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the number of balls of color i.
 * All the balls will be shuffled uniformly at random, then we will distribute the first n balls to the first box
 * and the remaining n balls to the other box (Please read the explanation of the second example carefully).
 * Please note that the two boxes are considered different. For example, if we have two balls of colors a and b,
 * and two boxes [] and (), then the distribution [a] (b) is considered different than the distribution [b] (a)
 * (Please read the explanation of the first example carefully).
 * We want to calculate the probability that the two boxes have the same number of distinct balls.
 *
 * Example 1:
 * Input: balls = [1,1]
 * Output: 1.00000
 * Explanation: Only 2 ways to divide the balls equally:
 * - A ball of color 1 to box 1 and a ball of color 2 to box 2
 * - A ball of color 2 to box 1 and a ball of color 1 to box 2
 * In both ways, the number of distinct colors in each box is equal. The probability is 2/2 = 1
 *
 * Example 2:
 * Input: balls = [2,1,1]
 * Output: 0.66667
 * Explanation: We have the set of balls [1, 1, 2, 3]
 * This set of balls will be shuffled randomly and we may have one of the 12 distinct shuffles with equale probability (i.e. 1/12):
 * [1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
 * After that we add the first two balls to the first box and the second two balls to the second box.
 * We can see that 8 of these 12 possible random distributions have the same number of distinct colors of balls in each box.
 * Probability is 8/12 = 0.66667
 */
public class ProbabilityOfTwoBoxesHavingTheSameNumberOfDistinctBalls {
    public static void main(String[] args) {
        System.out.println(getProbability(new int[]{1, 1}));
        System.out.println(getProbability(new int[]{2, 1, 1}));
    }

    static double[] factorial = new double[7];

    /**
     * ALL the balls are different even they are in the same color! Hope this helps.
     * The idea here is to get the total number of valid cases and then divide the total number of combinations for selecting n balls out of 2n balls.
     * To count the valid cases we can do as following
     * 1. For any color i, we can select j (0, or 1, or, 2 ... or balls[i]) balls and put to box A, and put the rest of balls in box B.
     * Take [2,1,1] for example:
     * 1.1 [2,1,1]: For color 0, we can put 0 or 1 or 2 balls in box A while put 2 or 1 or 0 in Box B
     * 1.2 [2,1,1]: For color 1, we can put 0 or 1 ball in A, and 1 or 0 in B
     * 1.3 [2,1,1]: For color 2, we can put 0 or 1 ball in A, and 1 or 0 in B
     * For each case here, we need to multiply the number of combinations for selecting j balls out of balls[i].
     * Because when we do the splitting, we only considered the count of balls, however there are C(balls[i], j) conbinations in total.
     * 2. We validate if the current division is valid by checking if the number of balls in A and B are
     * equal AND the distinct color count are the same when all the colors are processed.
     * To get the total number of conbinations using n balls from 2n balls. We use the C(m,k)= m!/(k!(m-k)!) formula. In our case, m= 2n and k = n.
     * Thus C(2n,n) = (2n!)/n!*(2n-n)! = (2n!)/(n!)*n! = (1 * 2 * 3 *... * 2n)/(1 * 2 * 3 ... n) ^ 2 = (n+1 * n+2 * ... * 2n) / (1 * 2 * 3 ... n)
     * Then the finaly probability is (# of valid cases)/(total # of combinations)
     */
    public static double getProbability(int[] balls) {
        factorial[0] = 1;
        for (int i = 1; i < 7; i++) {
            // this part is to calculate the conbinations for selecting j balls out of balls[i] for color i.
            factorial[i] = factorial[i - 1] * i;
        }

        // Following part to to count the total combinations = (n+1 * n+2 * ... * 2n) / (1 * 2 * 3 *...* n)
        int sum = 0;
        for (int ball : balls) {
            sum += ball;
        }
        double total = 1;
        for (int i = sum; i > sum / 2; i--) {
            total *= i;
        }
        for (int i = sum / 2; i > 0; i--) {
            total /= i;
        }

        double validNumber = dfs(balls, 0, 0, 0, 0, 0);

        return validNumber / total;
    }

    /**
     * c1, c2 are the counters of the number of balls in each box
     * d1, d2 are the distinct colors in each box
     * idx is the current color we are splitting
     */
    private static double dfs(int[] balls, int idx, int c1, int c2, int d1, int d2) {
        if (idx == balls.length) {
            if (c1 == c2 && d1 == d2) {
                return 1;
            } else {
                return 0;
            }
        }
        double result = 0;
        // For each color, we could select 0,1,2,...,balls[idx] and put in box A, and the rest in box B.
        // If we put 0 balls in A, then the distinct color count d1 will not increase.
        // If we put all balls[idx] in A and 0 in B, then the distinct color count d2 will not increase.
        // For other cases, we increase d1 and d2 by 1 and go ahead process next color by calling dfs method .
        for (int i = 0; i <= balls[idx]; i++) {
            // combination method is used to calculate the total number of combinations to select i balls from balls[idx].
            result += combination(balls[idx], i) * dfs(balls, idx + 1, c1 + i, c2 + balls[idx] - i, d1 + (i == 0 ? 0 : 1), d2 + (i == balls[idx] ? 0 : 1));
        }
        return result;
    }

    private static double combination(int n, int k) {
        return factorial[n] / (factorial[k] * factorial[n - k]);
    }
}
