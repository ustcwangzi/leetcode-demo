package com.wz.lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 * Given an array of integer coordinates queens that represents the positions of the Black Queens,
 * and a pair of coordinates king that represent the position of the White King, return the coordinates
 * of all the queens (in any order) that can attack the King.
 *
 * Example 1:
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * Output: [[0,1],[1,0],[3,3]]
 * Explanation:
 * The queen at [0,1] can attack the king cause they're in the same row.
 * The queen at [1,0] can attack the king cause they're in the same column.
 * The queen at [3,3] can attack the king cause they're in the same diagnal.
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at [0,1].
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at [1,0].
 * The queen at [2,4] can't attack the king cause it's not in the same row/column/diagnal as the king.
 *
 * Example 2:
 * Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * Output: [[2,2],[3,4],[4,4]]
 *
 * Example 3:
 * Input: queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],
 * [0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * Output: [[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 */
public class QueensThatCanAttackKing {
    public static void main(String[] args) {
        int[][] queens = new int[][]{
                {0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}
        };
        int[] king = new int[]{0, 0};
        System.out.println(queensAttackKing(queens, king));

        queens = new int[][]{
                {0, 0}, {1, 1}, {2, 2}, {3, 4}, {3, 5}, {4, 4}, {4, 5}
        };
        king = new int[]{3, 3};
        System.out.println(queensAttackKing(queens, king));
    }

    /**
     * 往king的上下左右，上左，上右，下左，下右八个方向移动，如果某个方向遇到 queen，则表示这个queen能攻击到king
     */
    public static List<List<Integer>> queensAttackKing(int[][] queens, int[] king) {
        boolean[][] hasQueen = new boolean[8][8];
        for (int[] array : queens) {
            hasQueen[array[0]][array[1]] = true;
        }

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        boolean[] visited = new boolean[8];
        List<List<Integer>> result = new LinkedList<>();
        for (int i = king[0], j = king[1], count = 1; count < 8; count++) {
            for (int k = 0; k < direction.length; k++) {
                int x = i + direction[k][0] * count;
                int y = j + direction[k][1] * count;
                if (x >= 0 && x < 8 && y >= 0 && y < 8 && hasQueen[x][y] && !visited[k]) {
                    visited[k] = true;
                    List<Integer> pair = new ArrayList<>();
                    pair.add(x);
                    pair.add(y);
                    result.add(pair);
                }
            }
        }
        return result;
    }
}
