package com.wz.array;

import java.util.Arrays;

/**
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 * 1. A stone '#'
 * 2. A stationary obstacle '*'
 * 3. Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity.
 * Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box.
 * Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 * Return an n x m matrix representing the box after the rotation described above.
 *
 * Example 1:
 * @see ../../../../resource/RotatingTheBox1.jpg
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 *
 * Example 2:
 * @see ../../../../resource/RotatingTheBox2.jpg
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 *
 * Example 3:
 * @see ../../../../resource/RotatingTheBox3.jpg
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 *
 * Constraints:
 * 1. m == box.length
 * 2. n == box[i].length
 * 3. 1 <= m, n <= 500
 * 4. box[i][j] is either '#', '*', or '.'.
 */
public class RotatingTheBox {
    public static void main(String[] args) {
        char[][] result = rotateTheBox(new char[][]{
                {'#', '#', '*', '.', '*', '.'},
                {'#', '#', '#', '*', '.', '.'},
                {'#', '#', '#', '.', '#', '.'}
        });
        for (char[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * box 是 m*n 的，result 是 n*m 的，box 第 i 行对应 result 第 m-1-i 列
     * 按行遍历 box，统计 stone 数量，遇到 obstacle 时，就将之前的 stone 填充到 result 的相应位置，并将 stone 数量重置为 0
     * 注意处理当前行不存在 obstacle 的场景
     */
    public static char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] result = new char[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(result[i], '.');
        }

        for (int i = 0; i < m; i++) {
            int stone = 0, j = 0;
            for (; j < n; j++) {
                if (box[i][j] == '#') {
                    stone++;
                } else if (box[i][j] == '*') {
                    // box 第 i 行对应 result 第 m-1-i 列
                    fillStone(result, m - 1 - i, stone, j);
                    stone = 0;
                }
            }
            // 当前行不存在 obstacle
            if (j == n) {
                fillStone(result, m - 1 - i, stone, j);
            }
        }
        return result;
    }

    /**
     * 填充 result 第 col 列的数据
     */
    private static void fillStone(char[][] result, int col, int stone, int obstacle) {
        if (obstacle < result.length) {
            result[obstacle][col] = '*';
        }
        for (int i = obstacle - 1; i >= obstacle - stone; i--) {
            result[i][col] = '#';
        }
    }
}
