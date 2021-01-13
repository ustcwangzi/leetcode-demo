package com.wz.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 * Example 1:
 * Input: A = [[0,1],[1,0]]
 * Output: 1
 *
 * Example 2:
 * Input: A = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 *
 * Constraints:
 * 1. 2 <= A.length == A[0].length <= 100
 * 2. A[i][j] == 0 or A[i][j] == 1
 */
public class ShortestBridge {
    public static void main(String[] args) {
        System.out.println(shortestBridge(new int[][]{{0, 1}, {1, 0}}));
    }

    /**
     * DFS + BFS
     * 遍历数组，找到第一个 1 的位置，然后对其调用 DFS 找出所有相连的1，放入到一个队列 queue 中，并且将该点的值改为 2，
     * 然后使用 BFS 进行层次遍历，每遍历一层，结果 result 都增加1，当遇到 1 时，直接返回 result 即可
     */
    public static int shortestBridge(int[][] A) {
        int result = 0, n = A.length, startX = -1, startY = -1;
        int[] dirX = {-1, 0, 1, 0}, dirY = {0, 1, 0, -1};
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i][j] == 0) {
                    continue;
                }
                startX = i;
                startY = j;
                break;
            }
            if (startX != -1) {
                break;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        dfs(A, startX, startY, queue);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; --i) {
                int cur = queue.poll();
                for (int k = 0; k < 4; ++k) {
                    int x = cur / n + dirX[k], y = cur % n + dirY[k];
                    if (x < 0 || x >= n || y < 0 || y >= n || A[x][y] == 2) {
                        continue;
                    }
                    if (A[x][y] == 1) {
                        return result;
                    }
                    A[x][y] = 2;
                    queue.add(x * n + y);
                }
            }
            result++;
        }

        return result;
    }

    private static void dfs(int[][] A, int x, int y, Queue<Integer> queue) {
        int n = A.length;
        if (x < 0 || x >= n || y < 0 || y >= n || A[x][y] == 0 || A[x][y] == 2) {
            return;
        }

        A[x][y] = 2;
        queue.add(x * n + y);
        dfs(A, x + 1, y, queue);
        dfs(A, x, y + 1, queue);
        dfs(A, x - 1, y, queue);
        dfs(A, x, y - 1, queue);
    }
}
