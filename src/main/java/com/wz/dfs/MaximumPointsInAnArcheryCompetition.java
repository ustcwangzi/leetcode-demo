package com.wz.dfs;

import java.util.Arrays;

/**
 * Alice and Bob are opponents in an archery competition. The competition has set the following rules:
 * 1. Alice first shoots numArrows arrows and then Bob shoots numArrows arrows.
 * 2. The points are then calculated as follows:
 *  - The target has integer scoring sections ranging from 0 to 11 inclusive.
 *  - For each section of the target with score k (in between 0 to 11), say Alice and Bob have shot ak and bk arrows on that section respectively.
 *    If ak >= bk, then Alice takes k points. If ak < bk, then Bob takes k points.
 *  - However, if ak == bk == 0, then nobody takes k points.
 * For example, if Alice and Bob both shot 2 arrows on the section with score 11, then Alice takes 11 points.
 * On the other hand, if Alice shot 0 arrows on the section with score 11 and Bob shot 2 arrows on that same section, then Bob takes 11 points.
 * You are given the integer numArrows and an integer array aliceArrows of size 12, which represents the number of arrows
 * Alice shot on each scoring section from 0 to 11. Now, Bob wants to maximize the total number of points he can obtain.
 * Return the array bobArrows which represents the number of arrows Bob shot on each scoring section from 0 to 11.
 * The sum of the values in bobArrows should equal numArrows.
 * If there are multiple ways for Bob to earn the maximum total points, return any one of them.
 *
 * Example 1:
 * @link ../../../../resource/MaximumPointsInAnArcheryCompetition1.jpg
 * Input: numArrows = 9, aliceArrows = [1,1,0,1,0,0,2,1,0,1,2,0]
 * Output: [0,0,0,0,1,1,0,0,1,2,3,1]
 * Explanation: The table above shows how the competition is scored.
 * Bob earns a total point of 4 + 5 + 8 + 9 + 10 + 11 = 47.
 * It can be shown that Bob cannot obtain a score higher than 47 points.
 *
 * Example 2:
 * @link ../../../../resource/MaximumPointsInAnArcheryCompetition2.jpg
 * Input: numArrows = 3, aliceArrows = [0,0,1,0,0,0,0,0,0,0,0,2]
 * Output: [0,0,0,0,0,0,0,0,1,1,1,0]
 * Explanation: The table above shows how the competition is scored.
 * Bob earns a total point of 8 + 9 + 10 = 27.
 * It can be shown that Bob cannot obtain a score higher than 27 points.
 *
 * Constraints:
 * 1. 1 <= numArrows <= 10^5
 * 2. aliceArrows.length == bobArrows.length == 12
 * 3. 0 <= aliceArrows[i], bobArrows[i] <= numArrows
 * 4. sum(aliceArrows[i]) == numArrows
 */
public class MaximumPointsInAnArcheryCompetition {
    public static void main(String[] args) {
        MaximumPointsInAnArcheryCompetition competition = new MaximumPointsInAnArcheryCompetition();
        System.out.println(Arrays.toString(competition.maximumBobPoints(9, new int[]{1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0})));
        competition = new MaximumPointsInAnArcheryCompetition();
        System.out.println(Arrays.toString(competition.maximumBobPoints(3, new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2})));
    }

    int max = 0;
    int[] result = new int[12];

    /**
     * 需要得到的是 bob 的最大得分，而不是仅仅是赢
     * 例如 numArrows=2, aliceArrows[1]=1, aliceArrows[11]=1, alice 总得分为 12
     * bobArrows[11]=2, bob 总得分为 11，同时由于会将 alice 的得分变为 1，bob 可以获胜
     * 但还有其他选择，bobArrows[9]=1, bobArrows[10]=1, 此时 bob 总得分为 19
     * 因此，对于每一个 index，有两种情况：
     * - 选择拿到该分数，需要比 alice[index] 多一，此时可以获取该点的得分，同时 numArrows 需要减少
     * - 选择忽略该分数，跳过该点，直接选择下一个点，此时不得分，numArrows 维持不变
     */
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        dfs(aliceArrows, new int[12], numArrows, 0, 11);
        return result;
    }

    private void dfs(int[] alice, int[] bob, int arrows, int point, int index) {
        if (index < 0 || arrows <= 0) {
            // 还有 arrows 剩余，但已经无法得分了，直接加到位置 0
            if (arrows > 0) {
                bob[0] += arrows;
            }
            // 更新最大结果
            if (point > max) {
                max = point;
                result = bob.clone();
            }
            return;
        }
        if (arrows >= alice[index] + 1) {
            bob[index] = alice[index] + 1;
            // 选择拿到该点
            dfs(alice, bob, arrows - bob[index], point + index, index - 1);
            bob[index] = 0;
        }
        // 选择忽略该点，直接到一个点
        dfs(alice, bob, arrows, point, index - 1);
        bob[index] = 0;
    }
}
