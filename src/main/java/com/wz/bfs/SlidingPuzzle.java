package com.wz.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0.
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved.
 * If it is impossible for the state of the board to be solved, return -1.
 *
 * Example 1:
 * @link ../../../../resource/SlidingPuzzle1.jpg
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 *
 * Example 2:
 * @link ../../../../resource/SlidingPuzzle2.jpg
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 *
 * Example 3:
 * @link ../../../../resource/SlidingPuzzle3.jpg
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 *
 * Constraints:
 * 1. board.length == 2
 * 2. board[i].length == 3
 * 3. 0 <= board[i][j] <= 5
 * 4. Each value board[i][j] is unique.
 */
public class SlidingPuzzle {
    public static void main(String[] args) {
        System.out.println(slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
        System.out.println(slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
        System.out.println(slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
    }

    private static final String TARGET = "123450";
    private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 将二维数组转换为字符串，目标是通过交换 '0' 与其他元素，得到 "123450"
     * 找到 '0' 的位置，通过 BFS 与其他元素进行交换，判断交换之后能否得到目标，每进行一次 BFS，交换次数加一
     */
    public static int slidingPuzzle(int[][] board) {
        StringBuilder builder = new StringBuilder();
        for (int[] array : board) {
            for (int j = 0; j < board[0].length; j++) {
                builder.append(array[j]);
            }
        }
        if (TARGET.equals(builder.toString())) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(builder.toString());
        visited.add(builder.toString());
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                if (TARGET.equals(cur)) {
                    return level;
                }

                int indexZero = cur.indexOf('0');
                // board[i][j] == 0
                int i = indexZero / board[0].length, j = indexZero % board[0].length;
                for (int[] dir : DIRS) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                        continue;
                    }

                    char[] array = cur.toCharArray();
                    // '0' 与其他元素交换
                    array[indexZero] = array[x * board[0].length + y];
                    array[x * board[0].length + y] = '0';
                    String next = new String(array);
                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
