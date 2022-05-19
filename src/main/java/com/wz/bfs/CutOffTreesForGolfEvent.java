package com.wz.bfs;

import java.util.*;

/**
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:
 * - 0 means the cell cannot be walked through.
 * - 1 represents an empty cell that can be walked through.
 * - A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
 * In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.
 * You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).
 * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.
 * You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.
 *
 * Example 1:
 * @link ../../../../resource/TrappingRainWater.jpg
 * Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
 * Output: 6
 * Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
 *
 * Example 2:
 * @link ../../../../resource/TrappingRainWater.jpg
 * Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
 * Output: -1
 * Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.
 *
 * Example 3:
 * Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
 * Output: 6
 * Explanation: You can follow the same path as Example 1 to cut off all the trees.
 * Note that you can cut off the first tree at (0, 0) before making any steps.
 *
 * Constraints:
 * 1. m == forest.length
 * 2. n == forest[i].length
 * 3. 1 <= m, n <= 50
 * 4. 0 <= forest[i][j] <= 10^9
 */
public class CutOffTreesForGolfEvent {
    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(1, 2, 3));
        forest.add(Arrays.asList(0, 0, 4));
        forest.add(Arrays.asList(7, 6, 5));
        System.out.println(cutOffTree(forest));

        forest = new ArrayList<>();
        forest.add(Arrays.asList(1, 2, 3));
        forest.add(Arrays.asList(0, 0, 0));
        forest.add(Arrays.asList(7, 6, 5));
        System.out.println(cutOffTree(forest));
    }

    private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 要按从低到高砍掉所有高度大于 1 的树
     * 按树高从低到高排序然后从{0,0}开始, 每次用 BFS 求出到下一棵树的最短距离，然后累加，若不能到达 下一棵树, 则返回 - 1
     */
    public static int cutOffTree(List<List<Integer>> forest) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    list.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        list.sort(Comparator.comparingInt(o -> o[2]));
        int[] cur = new int[]{0, 0};
        int result = 0;
        for (int[] array : list) {
            int[] next = new int[]{array[0], array[1]};
            int step = getStep(cur, next, forest);
            if (step == -1) {
                return -1;
            }
            result += step;
            cur = next;
        }
        return result;
    }

    private static int getStep(int[] start, int[] next, List<List<Integer>> forest) {
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == next[0] && cur[1] == next[1]) {
                return cur[2];
            }
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            for (int[] dir : DIRS) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x >= 0 && x < forest.size() && y >= 0 && y < forest.get(0).size() && forest.get(x).get(y) != 0) {
                    queue.offer(new int[]{x, y, cur[2] + 1});
                }
            }
        }
        return -1;
    }
}
