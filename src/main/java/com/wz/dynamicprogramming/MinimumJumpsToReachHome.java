package com.wz.dynamicprogramming;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 * The bug jumps according to the following rules:
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i],
 * and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home.
 * If there is no possible sequence of jumps that lands the bug on position x, return -1.
 *
 * Example 1:
 * Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * Output: 3
 * Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
 *
 * Example 2:
 * Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * Output: -1
 *
 * Example 3:
 * Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * Output: 2
 * Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 *
 * Constraints:
 * 1. 1 <= forbidden.length <= 1000
 * 2. 1 <= a, b, forbidden[i] <= 2000
 * 3. 0 <= x <= 2000
 * 4. All the elements in forbidden are distinct.
 * 5. Position x is not forbidden.
 */
public class MinimumJumpsToReachHome {
    public static void main(String[] args) {
        System.out.println(minimumJumps(new int[]{14, 4, 18, 1, 15}, 3, 15, 9));
    }

    /**
     * BFS
     * 用 visited 记录每个位置是否走过，用 queue 记录到达的位置
     */
    public static int minimumJumps(int[] forbidden, int a, int b, int x) {
        int furthest = 2001 + a + b;
        boolean[][] visited = new boolean[furthest][2];
        for (int val : forbidden) {
            visited[val][0] = true;
            visited[val][1] = true;
        }
        int step = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int cur = queue.poll();
                if (Math.abs(cur) == x) {
                    return step;
                }

                // 向右
                if (Math.abs(cur) + a < furthest && !visited[Math.abs(cur) + a][0]) {
                    queue.add(Math.abs(cur) + a);
                    visited[Math.abs(cur) + a][0] = true;
                }
                // 向左
                if (cur - b >= 0 && !visited[cur - b][1]) {
                    queue.add(-(cur - b));
                    visited[cur - b][1] = true;
                }
            }
            step++;
        }
        return -1;
    }
}
