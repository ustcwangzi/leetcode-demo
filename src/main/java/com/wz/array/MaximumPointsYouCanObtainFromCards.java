package com.wz.array;

import java.util.Arrays;

/**
 * There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 * Example 1:
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize
 * your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 *
 * Example 2:
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 *
 * Example 3:
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 *
 * Example 4:
 * Input: cardPoints = [1,1000,1], k = 1
 * Output: 1
 * Explanation: You cannot take the card in the middle. Your best score is 1.
 *
 * Example 5:
 * Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * Output: 202
 *
 * Constraints:
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 */
public class MaximumPointsYouCanObtainFromCards {
    public static void main(String[] args) {
        int[] cardPoints = new int[]{1, 2, 3, 4, 5, 6, 1};
        System.out.println(maxScore(cardPoints, 3));

        cardPoints = new int[]{2, 2, 2};
        System.out.println(maxScore(cardPoints, 2));

        cardPoints = new int[]{9, 7, 7, 9, 7, 7, 9};
        System.out.println(maxScore(cardPoints, 7));

        cardPoints = new int[]{1, 1000, 1};
        System.out.println(maxScore(cardPoints, 1));

        cardPoints = new int[]{1, 79, 80, 1, 1, 1, 200, 1};
        System.out.println(maxScore(cardPoints, 3));
    }

    /**
     * 每次只能从数组的两端取一张卡牌，因此在 K 张卡牌全部取完之后，肯定是在数组的左端取了连续的 m 张卡牌，在数组的右端取了连续的 n 张卡牌
     * 其中 m+n=k，因此初始时，可以令 m = k，n = 0，并且计算出当前这 k 张卡牌的总和
     * 然后循环，每一步为 m 减一，n 加一，并更新当前的总和，同时更新全局最大的总和，直到循环至 m == 0，n == k为止
     */
    public static int maxScore(int[] cardPoints, int k) {
        if (k == 1) {
            return Math.max(cardPoints[0], cardPoints[cardPoints.length - 1]);
        }
        if (k == cardPoints.length) {
            return Arrays.stream(cardPoints).sum();
        }

        // 前 k 张牌的总和
        int score = 0;
        for (int i = 0; i < k; i++) {
            score += cardPoints[i];
        }

        int result = score;
        // 初始时，左边选 k 张牌，右边选 0 张牌
        int left = k - 1, right = cardPoints.length;
        while (left >= 0) {
            // 从左边减少一张牌
            score -= cardPoints[left--];
            // 从右边增加一张牌
            score += cardPoints[--right];
            result = Math.max(result, score);
        }
        return result;
    }
}
