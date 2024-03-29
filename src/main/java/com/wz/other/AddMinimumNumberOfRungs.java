package com.wz.other;

/**
 * You are given a strictly increasing integer array rungs that represents the height of rungs on a ladder.
 * You are currently on the floor at height 0, and you want to reach the last rung.
 * You are also given an integer dist. You can only climb to the next highest rung
 * if the distance between where you are currently at (the floor or on a rung) and the next rung is at most dist.
 * You are able to insert rungs at any positive integer height if a rung is not already there.
 * Return the minimum number of rungs that must be added to the ladder in order for you to climb to the last rung.
 *
 * Example 1:
 * Input: rungs = [1,3,5,10], dist = 2
 * Output: 2
 * Explanation:
 * You currently cannot reach the last rung.
 * Add rungs at heights 7 and 8 to climb this ladder.
 * The ladder will now have rungs at [1,3,5,7,8,10].
 *
 * Example 2:
 * Input: rungs = [3,6,8,10], dist = 3
 * Output: 0
 * Explanation:
 * This ladder can be climbed without adding additional rungs.
 *
 * Example 3:
 * Input: rungs = [5], dist = 10
 * Output: 0
 * Explanation:
 * This ladder can be climbed without adding additional rungs.
 *
 * Constraints:
 * 1. 1 <= rungs.length <= 10^5
 * 2. 1 <= rungs[i] <= 10^9
 * 3. 1 <= dist <= 10^9
 * 4. rungs is strictly increasing.
 */
public class AddMinimumNumberOfRungs {
    public static void main(String[] args) {
        System.out.println(addRungs(new int[]{1, 3, 5, 10}, 2));
        System.out.println(addRungs(new int[]{3}, 1));
    }

    /**
     * 每个台阶最多上升 dist，当高度差小于等于 dist 时无需增加台阶
     * 因此每次需要增加的台阶数为 (rungs[i] - rungs[i-1] - 1) / dist
     */
    public static int addRungs(int[] rungs, int dist) {
        int result = (rungs[0] - 1) / dist;
        for (int i = 1; i < rungs.length; i++) {
            result += (rungs[i] - rungs[i - 1] - 1) / dist;
        }
        return result;
    }
}
