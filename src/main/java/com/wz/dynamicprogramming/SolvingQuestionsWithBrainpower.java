package com.wz.dynamicprogramming;

/**
 * You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
 * The array describes the questions of an exam, where you have to process the questions in order
 * (i.e., starting from question 0) and make a decision whether to solve or skip each question.
 * Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions.
 * If you skip question i, you get to make the decision on the next question.
 * For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
 * - If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
 * - If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
 * Return the maximum points you can earn for the exam.
 *
 * Example 1:
 * Input: questions = [[3,2],[4,3],[4,4],[2,5]]
 * Output: 5
 * Explanation: The maximum points can be earned by solving questions 0 and 3.
 * - Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
 * - Unable to solve questions 1 and 2
 * - Solve question 3: Earn 2 points
 * Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
 *
 * Example 2:
 * Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
 * Output: 7
 * Explanation: The maximum points can be earned by solving questions 1 and 4.
 * - Skip question 0
 * - Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
 * - Unable to solve questions 2 and 3
 * - Solve question 4: Earn 5 points
 * Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.
 *
 * Constraints:
 * 1. 1 <= questions.length <= 10^5
 * 2. questions[i].length == 2
 * 3. 1 <= pointsi, brainpoweri <= 10^5
 */
public class SolvingQuestionsWithBrainpower {
    public static void main(String[] args) {
        System.out.println(mostPoints(new int[][]{{3, 2}, {4, 3}, {4, 4}, {2, 5}}));
    }

    /**
     * 解决 questions[i] 能够得到 point[i]，接下来的 brainpower[i] 个问题需要跳过
     * 动态规划， 对于 questions[i]，有解决、不解决两种选择
     * - 解决得分为 point[i]+dp[x+1]，x 为需要跳过的个数
     * - 不解决，得分为 dp[i+1]
     * 可以看到 dp[i] 依赖了 dp[x+1]，因此可以对数组从右向左遍历，dp[i] 表示解决 [i...n-1] 的最高得分
     */
    public static long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        dp[n - 1] = questions[n - 1][0];
        for (int i = n - 2; i >= 0; i--) {
            // 下一个可以解决的问题
            int nextIndex = questions[i][1] + i + 1;
            nextIndex = nextIndex >= n ? 0 : nextIndex;
            dp[i] = Math.max(questions[i][0] + dp[nextIndex], dp[i + 1]);
        }
        return dp[0];
    }
}
