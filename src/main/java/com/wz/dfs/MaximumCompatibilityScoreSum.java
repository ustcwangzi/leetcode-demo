package com.wz.dfs;

import java.util.Arrays;

/**
 * There is a survey that consists of n questions where each question's answer is either 0 (no) or 1 (yes).
 * The survey was given to m students numbered from 0 to m - 1 and m mentors numbered from 0 to m - 1.
 * The answers of the students are represented by a 2D integer array students where students[i] is an integer array that contains the answers of the ith student (0-indexed).
 * The answers of the mentors are represented by a 2D integer array mentors where mentors[j] is an integer array that contains the answers of the jth mentor (0-indexed).
 * Each student will be assigned to one mentor, and each mentor will have one student assigned to them.
 * The compatibility score of a student-mentor pair is the number of answers that are the same for both the student and the mentor.
 * For example, if the student's answers were [1, 0, 1] and the mentor's answers were [0, 0, 1],
 * then their compatibility score is 2 because only the second and the third answers are the same.
 * You are tasked with finding the optimal student-mentor pairings to maximize the sum of the compatibility scores.
 * Given students and mentors, return the maximum compatibility score sum that can be achieved.
 *
 * Example 1:
 * Input: students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
 * Output: 8
 * Explanation: We assign students to mentors in the following way:
 * - student 0 to mentor 2 with a compatibility score of 3.
 * - student 1 to mentor 0 with a compatibility score of 2.
 * - student 2 to mentor 1 with a compatibility score of 3.
 * The compatibility score sum is 3 + 2 + 3 = 8.
 *
 * Example 2:
 * Input: students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]]
 * Output: 0
 * Explanation: The compatibility score of any student-mentor pair is 0.
 *
 * Constraints:
 * 1. m == students.length == mentors.length
 * 2. n == students[i].length == mentors[j].length
 * 3. 1 <= m, n <= 8
 * 4. students[i][k] is either 0 or 1.
 * 5. mentors[j][k] is either 0 or 1.
 */
public class MaximumCompatibilityScoreSum {
    public static void main(String[] args) {
        System.out.println(maxCompatibilitySum(new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 1}}, new int[][]{{1, 0, 0}, {0, 0, 1}, {1, 1, 0}}));
    }

    /**
     * 对 student 进行 DFS，分别去匹配每个 mentor，并更新最大得分
     */
    public static int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int[] score = new int[mentors.length];
        Arrays.fill(score, -1);
        return dfs(0, score, students, mentors);
    }

    private static int dfs(int index, int[] score, int[][] students, int[][] mentors) {
        if (index == score.length) {
            return Arrays.stream(score).sum();
        }

        int maxSum = 0;
        for (int i = 0; i < score.length; i++) {
            if (score[i] != -1) {
                continue;
            }
            int sum = 0;
            for (int j = 0; j < mentors[0].length; j++) {
                if (students[index][j] == mentors[i][j]) {
                    sum++;
                }
            }
            score[i] = sum;
            maxSum = Math.max(maxSum, dfs(index + 1, score, students, mentors));
            score[i] = -1;
        }
        return maxSum;
    }
}
