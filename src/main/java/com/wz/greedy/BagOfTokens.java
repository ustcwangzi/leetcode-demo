package com.wz.greedy;

import java.util.Arrays;

/**
 * You have an initial power of P, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).
 * Your goal is to maximize your total score by potentially playing each token in one of two ways:
 * If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
 * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
 * Each token may be played at most once and in any order. You do not have to play all the tokens.
 * Return the largest possible score you can achieve after playing any number of tokens.
 *
 * Example 1:
 * Input: tokens = [100,200], P = 150
 * Output: 1
 * Explanation: Play the 0th token (100) face up, your power becomes 50 and score becomes 1.
 * There is no need to play the 1st token since you cannot play it face up to add to your score.
 *
 * Example 2:
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 * Explanation: Play the tokens in this order to get a score of 2:
 * 1. Play the 0th token (100) face up, your power becomes 100 and score becomes 1.
 * 2. Play the 3rd token (400) face down, your power becomes 500 and score becomes 0.
 * 3. Play the 1st token (200) face up, your power becomes 300 and score becomes 1.
 * 4. Play the 2nd token (300) face up, your power becomes 0 and score becomes 2.
 *
 * Constraints:
 * 1. 0 <= tokens.length <= 1000
 * 2. 0 <= tokens[i], P < 10^4
 */
public class BagOfTokens {
    public static void main(String[] args) {
        System.out.println(bagOfTokensScore(new int[]{100, 200}, 150));
        System.out.println(bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
    }

    /**
     * 有两种操作可以选择，一种是减去 tokens[i]，得到一分，前提是减去后剩余的力量不能为负
     * 另一种是减去一分，得到 tokens[i]，前提是减去后的分数不能为负，问可以得到的最高分数
     * 基本思路是用 P 减去较小的 token 得到分数，当小于 token 时，用分数换 token
     * 从最小的 token 开始，用力量换积分，当力量不够时，就用积分换最大的力量，如果没有积分可以换力量或者 tokens 遍历结束，就结束
     * 先对 tokens 进行排序，然后使用 left、right 分别指向开头和末尾，当 left<=right 时循环，
     * 从小的 token 开始查找，只要力量够，就换成积分，不能换的时候，假如积分大于 0，则用积分换最大的力量，否则直接结束
     */
    public static int bagOfTokensScore(int[] tokens, int P) {
        Arrays.parallelSort(tokens);

        int result = 0, cur = 0, left = 0, right = tokens.length - 1;
        while (left <= right) {
            if (P >= tokens[left]) {
                P -= tokens[left++];
                result = Math.max(result, ++cur);
            } else if (cur > 0) {
                cur--;
                P += tokens[right--];
            } else {
                break;
            }
        }

        return result;
    }
}
