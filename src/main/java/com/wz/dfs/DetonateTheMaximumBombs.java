package com.wz.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri].
 * xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range.
 * These bombs will further detonate the bombs that lie in their ranges.
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 *
 * Example 1:
 * @link ../../../../resource/DetonateTheMaximumBombs1.jpg
 * Input: bombs = [[2,1,3],[6,1,4]]
 * Output: 2
 * Explanation:
 * The above figure shows the positions and ranges of the 2 bombs.
 * If we detonate the left bomb, the right bomb will not be affected.
 * But if we detonate the right bomb, both bombs will be detonated.
 * So the maximum bombs that can be detonated is max(1, 2) = 2.
 *
 * Example 2:
 * @link ../../../../resource/DetonateTheMaximumBombs2.jpg
 * Input: bombs = [[1,1,5],[10,10,5]]
 * Output: 1
 * Explanation:
 * Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
 *
 * Example 3:
 * @link ../../../../resource/DetonateTheMaximumBombs3.jpg
 * Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
 * Output: 5
 * Explanation:
 * The best bomb to detonate is bomb 0 because:
 * - Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
 * - Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
 * - Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
 * Thus all 5 bombs are detonated.
 *
 * Constraints:
 * 1. 1 <= bombs.length <= 100
 * 2. bombs[i].length == 3
 * 3. 1 <= xi, yi, ri <= 10^5
 */
public class DetonateTheMaximumBombs {
    public static void main(String[] args) {
        System.out.println(maximumDetonation(new int[][]{{2, 1, 3}, {6, 1, 4}}));
        System.out.println(maximumDetonation(new int[][]{{1, 2, 3}, {2, 1, 3}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}}));
    }

    /**
     * 遍历 bombs，对每个 bomb 进行 dfs，求出该 bomb 能覆盖的个数，即 (xi - xj)^2 + (yi - yj)^2 <= r^2
     */
    public static int maximumDetonation(int[][] bombs) {
        int result = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < bombs.length; i++) {
            set.clear();
            dfs(bombs, i, set);
            result = Math.max(result, set.size());
        }
        return result;
    }

    private static void dfs(int[][] bombs, int curIndex, Set<Integer> visited) {
        if (!visited.add(curIndex)) {
            return;
        }
        int[] cur = bombs[curIndex];
        for (int i = 0; i < bombs.length; i++) {
            double distance = Math.pow(cur[0] - bombs[i][0], 2) + Math.pow(cur[1] - bombs[i][1], 2);
            if (distance <= Math.pow(cur[2], 2)) {
                dfs(bombs, i, visited);
            }
        }
    }
}
