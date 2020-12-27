package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score.
 * The score of the team is the sum of scores of all the players in the team.
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a
 * strictly higher score than an older player. A conflict does not occur between players of the same age.
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player,
 * respectively, return the highest overall score of all possible basketball teams.
 *
 * Example 1:
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 *
 * Example 2:
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
 *
 * Example 3:
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 *
 * Constraints:
 * 1. 1 <= scores.length, ages.length <= 1000
 * 2. scores.length == ages.length
 * 3. 1 <= scores[i] <= 106
 * 4. 1 <= ages[i] <= 1000
 */
public class BestTeamWithNoConflicts {
    public static void main(String[] args) {
        System.out.println(bestTeamScore(new int[]{4, 5, 6, 5}, new int[]{2, 1, 2, 1}));
    }

    /**
     * 年龄小的得分不能大于年龄大的
     * dp[i] 表示以 i 结尾并且选择 i 情况下的最大得分
     * 使用 Pair 数组保存得分和年龄情况，按照年龄、得分进行排序，这样年龄小的全部排在前面
     * 遍历 Pair 数组，对于 i，遍历之前的 j，若 score[j] <= score[i]，则两者可以同时选择，更新 dp[i] = max{dp[i], dp[j] + pairs[i].score}
     */
    public static int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length, result = 0;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(scores[i], ages[i]);
        }
        Arrays.parallelSort(pairs, (o1, o2) -> o1.age != o2.age ? o1.age - o2.age : o1.score - o2.score);

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = pairs[i].score;
            for (int j = 0; j < i; j++) {
                if (pairs[j].score <= pairs[i].score) {
                    dp[i] = Math.max(dp[i], dp[j] + pairs[i].score);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    static class Pair {
        int score;
        int age;

        public Pair(int score, int age) {
            this.score = score;
            this.age = age;
        }
    }
}
