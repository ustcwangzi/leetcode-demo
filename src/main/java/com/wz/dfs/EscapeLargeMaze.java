package com.wz.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).
 * We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square.
 * There is also an array of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).
 * Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked squares.
 * We are also not allowed to walk outside of the grid.
 * Return true if and only if it is possible to reach the target square from the source square through a sequence of valid moves.
 *
 * Example 1:
 * Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * Output: false
 * Explanation: The target square is inaccessible starting from the source square because we cannot move.
 * We cannot move north or east because those squares are blocked.
 * We cannot move south or west because we cannot go outside of the grid.
 *
 * Example 2:
 * Input: blocked = [], source = [0,0], target = [999999,999999]
 * Output: true
 * Explanation: Because there are no blocked cells, it is possible to reach the target square.
 *
 * Constraints:
 * 1. 0 <= blocked.length <= 200
 * 2. blocked[i].length == 2
 * 3. 0 <= xi, yi < 10^6
 * 4. source.length == target.length == 2
 * 5. 0 <= sx, sy, tx, ty < 10^6
 * 6. source != target
 * 7. It is guaranteed that source and target are not blocked.
 */
public class EscapeLargeMaze {
    public static void main(String[] args) {
        System.out.println(isEscapePossible(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2}));
        System.out.println(isEscapePossible(new int[][]{}, new int[]{0, 0}, new int[]{999999, 999999}));
    }

    private static final long MAX = 1000000;
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 只要 source 和 target 不被完全围住，就能够到达，blocked 最大只有 200，因此只要超过 blocked 的范围就说明没被围住
     * 分别从 source 出发、从 target 判断有没有被围住，为加快搜索可以将二维坐标转换为一维，同时记录已遍历的点避免死循环
     */
    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockSet = new HashSet<>(blocked.length);
        for (int[] block : blocked) {
            blockSet.add(block[0] * MAX + block[1]);
        }
        return dfs(blockSet, source, target, source, new HashSet<>())
                && dfs(blockSet, target, source, target, new HashSet<>());
    }

    private static boolean dfs(Set<Long> blocked, int[] source, int[] target, int[] cur, Set<Long> visited) {
        // 已超出 block 范围
        if (Math.abs(cur[0] - source[0]) == 200 || Math.abs(cur[1] - source[1]) == 200) {
            return true;
        }
        // 已达到指定位置
        if (visited.size() > 0 && cur[0] == target[0] && cur[1] == target[1]) {
            return true;
        }

        visited.add(cur[0] * MAX + cur[1]);
        for (int[] dir : DIRS) {
            int x = cur[0] + dir[0], y = cur[1] + dir[1];
            // 超过边界
            if (x < 0 || x >= MAX || y < 0 || y >= MAX || visited.contains(x * MAX + y) || blocked.contains(x * MAX + y)) {
                continue;
            }
            if (dfs(blocked, source, target, new int[]{x, y}, visited)) {
                return true;
            }
        }
        return false;
    }
}
