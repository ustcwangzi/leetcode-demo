package com.wz.dynamicprogramming;

/**
 * Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken,
 * it is denoted by '#' character otherwise it is denoted by a '.' character.
 * Students can see the answers of those sitting next to the left, right, upper left and upper right,
 * but he cannot see the answers of the student sitting directly in front or behind him.
 * Return the maximum number of students that can take the exam together without any cheating being possible.
 * Students must be placed in seats in good condition.
 *
 * Example 1:
 * @link ../../../../resource/MaximumStudentsTakingExam.jpg
 * Input: seats = [["#",".","#","#",".","#"],
 *                 [".","#","#","#","#","."],
 *                 ["#",".","#","#",".","#"]]
 * Output: 4
 * Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.
 *
 * Example 2:
 * Input: seats = [[".","#"],
 *                 ["#","#"],
 *                 ["#","."],
 *                 ["#","#"],
 *                 [".","#"]]
 * Output: 3
 * Explanation: Place all students in available seats.
 *
 * Constraints:
 * 1. seats contains only characters '.' and'#'.
 * 2. m == seats.length
 * 3. n == seats[i].length
 * 4. 1 <= m <= 8
 * 5. 1 <= n <= 8
 */
public class MaximumStudentsTakingExam {
    public static void main(String[] args) {
        char[][] seats = new char[][]{
                {'#', '.', '#', '#', '.', '#'},
                {'.', '#', '#', '#', '#', '.'},
                {'#', '.', '#', '#', '.', '#'},
        };
        System.out.println(maxStudents(seats));
    }

    /**
     * dp[i][j] = max{dp[i][j], dp[i - 1][k] + CountBinary1(j)}
     * 其中 i 表示第几排，j、k 表示该排座位状态压缩后的值，j、k 布局应当满足题目要求
     */
    public static int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        // dp means ith row's original state, available spot filled with '1' otherwise '0', may not be a valid state
        int[] dp = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i] = (dp[i] << 1) + (seats[i - 1][j] == '.' ? 1 : 0);
            }
        }
        int result = 0, state = 1 << n;
        int[][] max = new int[m + 1][state];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < state; j++) {
                if ((dp[i] & j) == j && (j & (j >> 1)) == 0) {
                    for (int k = 0; k < state; k++) {
                        if ((k & dp[i - 1]) == k && ((k >> 1) & j) == 0 && ((j >> 1) & k) == 0)
                            max[i][j] = Math.max(max[i][j], max[i - 1][k] + Integer.bitCount(j));
                    }
                    result = Math.max(result, max[i][j]);
                }
            }
        }
        return result;
    }
}
