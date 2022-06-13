package com.wz.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an m x n grid grid where:
 * - '.' is an empty cell.
 * - '#' is a wall.
 * - '@' is the starting point.
 * - Lowercase letters represent keys.
 * - Uppercase letters represent locks.
 * You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.
 * If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.
 * For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid.
 * This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
 * Return the lowest number of moves to acquire all keys. If it is impossible, return -1.
 *
 * Example 1:
 * Input: grid = ["@.a..","###.#","b.A.B"]
 * Output: 8
 * Explanation: Note that the goal is to obtain all the keys not to open all the locks.
 *
 * Example 2:
 * Input: grid = ["@..aA","..B#.","....b"]
 * Output: 6
 *
 * Example 3:
 * Input: grid = ["@Aa"]
 * Output: -1
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 30
 * 4. grid[i][j] is either an English letter, '.', '#', or '@'.
 * 5. The number of keys in the grid is in the range [1, 6].
 * 6. Each key in the grid is unique.
 * 7. Each key in the grid has a matching lock.
 */
public class ShortestPathToGetAllKeys {
    public static void main(String[] args) {
        System.out.println(shortestPathAllKeys(new String[]{"@.a..", "###.#", "b.A.B"}));
        System.out.println(shortestPathAllKeys(new String[]{"@..aA", "..B#.", "....b"}));
        System.out.println(shortestPathAllKeys(new String[]{"@Aa"}));
    }

    private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * BFS
     * 目标是找到全部钥匙，对于锁，需要已经有相应的 key 才能通行
     * 使用 keys 来保存当前已经找到的钥匙，初识时将 @ 所在的位置加入队列，然后按层遍历，每遍历一层 level++
     * 找到全部钥匙，则返回当前 level，遍历结束还未找到则返回 -1
     */
    public static int shortestPathAllKeys(String[] grid) {
        char[][] matrix = buildMatrix(grid);
        int m = matrix.length, n = matrix[0].length, keyCount = 0, level = 0;
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '@') {
                    queue.offer(new Node(i, j));
                    visited.add(buildState(i, j, ""));
                } else if (Character.isLowerCase(matrix[i][j])) {
                    keyCount++;
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                // 加入钥匙
                if (Character.isLowerCase(matrix[cur.i][cur.j]) && cur.keys.indexOf(matrix[cur.i][cur.j]) == -1) {
                    cur.keys += matrix[cur.i][cur.j];
                }
                if (cur.keys.length() == keyCount) {
                    return level;
                }

                for (int[] dir : DIRS) {
                    int x = cur.i + dir[0], y = cur.j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] != '#') {
                        // 当前锁还没有对应的钥匙
                        if (Character.isUpperCase(matrix[x][y]) && cur.keys.indexOf(Character.toLowerCase(matrix[x][y])) == -1) {
                            continue;
                        }

                        String state = buildState(x, y, cur.keys);
                        if (visited.contains(state)) {
                            continue;
                        }
                        queue.offer(new Node(x, y, cur.keys));
                        visited.add(state);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private static String buildState(int i, int j, String keys) {
        return i + "-" + j + "-" + keys;
    }

    private static char[][] buildMatrix(String[] grid) {
        char[][] matrix = new char[grid.length][grid[0].length()];
        for (int i = 0; i < matrix.length; i++) {
            String str = grid[i];
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = str.charAt(j);
            }
        }
        return matrix;
    }

    private static class Node {
        private final int i;
        private final int j;
        private String keys;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
            keys = "";
        }

        public Node(int i, int j, String keys) {
            this.i = i;
            this.j = j;
            this.keys = keys;
        }
    }
}
