package com.wz.other;

/**
 * There is an m x n grid, where (0, 0) is the top-left cell and (m - 1, n - 1) is the bottom-right cell.
 * You are given an integer array startPos where startPos = [startrow, startcol] indicates that initially,
 * a robot is at the cell (startrow, startcol). You are also given an integer array homePos where homePos = [homerow, homecol]
 * indicates that its home is at the cell (homerow, homecol).
 * The robot needs to go to its home. It can move one cell in four directions: left, right, up, or down,
 * and it can not move outside the boundary. Every move incurs some cost.
 * You are further given two 0-indexed integer arrays: rowCosts of length m and colCosts of length n.
 * 1. If the robot moves up or down into a cell whose row is r, then this move costs rowCosts[r].
 * 2. If the robot moves left or right into a cell whose column is c, then this move costs colCosts[c].
 * Return the minimum total cost for this robot to return home.
 *
 * Example 1:
 * @link ../../../../resource/MagneticForceBetweenTwoBalls.jpg
 * Input: startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
 * Output: 18
 * Explanation: One optimal path is that:
 * Starting from (1, 0)
 * -> It goes down to (2, 0). This move costs rowCosts[2] = 3.
 * -> It goes right to (2, 1). This move costs colCosts[1] = 2.
 * -> It goes right to (2, 2). This move costs colCosts[2] = 6.
 * -> It goes right to (2, 3). This move costs colCosts[3] = 7.
 * The total cost is 3 + 2 + 6 + 7 = 18
 *
 * Example 2:
 * Input: startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
 * Output: 0
 * Explanation: The robot is already at its home. Since no moves occur, the total cost is 0.
 *
 * Constraints:
 * 1. m == rowCosts.length
 * 2. n == colCosts.length
 * 3. 1 <= m, n <= 10^5
 * 4. 0 <= rowCosts[r], colCosts[c] <= 10^4
 * 5. startPos.length == 2
 * 6. homePos.length == 2
 * 7. 0 <= startrow, homerow < m
 * 8. 0 <= startcol, homecol < n
 */
public class MinimumCostHomecomingOfRobotInGrid {
    public static void main(String[] args) {
        System.out.println(minCost(new int[]{1, 0}, new int[]{2, 3}, new int[]{5, 4, 3}, new int[]{8, 2, 6, 7}));
    }

    /**
     * 直接将起点到终点的行cost、列cost 进行累加即可，因为：
     * 1. 并不是每个节点都有 cost，而是相同行的节点以及相同列的节点 cost 都相同
     * 2. 从起点到终点，这两个之间夹的行和列必须经过
     * 2. 行和列的 cost 都为非负数，所以每一行或者列都只需经过一次，没有可能经过两次
     * 因此，只需要按照起点到终点的顺序把行和列的 cost 求和即可。注意的，起始位置的行和列并不一定比终止位置的行和列小
     */
    public static int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        // 根据起点、终点位置的大小求出移动的方向
        int directRow = startPos[0] > homePos[0] ? -1 : 1, directCol = startPos[1] > homePos[1] ? -1 : 1, result = 0;
        int row = startPos[0], col = startPos[1];
        // 将所经过的行、列 cost 进行累加
        while (row != homePos[0]) {
            row += directRow;
            result += rowCosts[row];
        }
        while (col != homePos[1]) {
            col += directCol;
            result += colCosts[col];
        }
        return result;
    }
}
