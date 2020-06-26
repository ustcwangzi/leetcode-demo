package com.wz.lists;

/**
 * An N x N board contains only 0s and 1s.
 * In each move, you can swap any 2 rows with each other, or any 2 columns with each other.
 * What is the minimum number of moves to transform the board into a "chessboard"
 * - a board where no 0s and no 1s are 4-directionally adjacent? If the task is impossible, return -1.
 *
 * Examples:
 * Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * Output: 2
 * Explanation:
 * One potential sequence of moves is shown below, from left to right:
 * 0110     1010     1010
 * 0110 --> 1010 --> 0101
 * 1001     0101     1010
 * 1001     0101     0101
 * The first move swaps the first and second column.
 * The second move swaps the second and third row.
 *
 * Input: board = [[0, 1], [1, 0]]
 * Output: 0
 * Explanation:
 * Also note that the board with 0 in the top left corner,
 * 01
 * 10
 * is also a valid chessboard.
 *
 * Input: board = [[1, 0], [1, 0]]
 * Output: -1
 * Explanation:
 * No matter what sequence of moves you make, you cannot end with a valid chessboard.
 */
public class TransformToChessboard {
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 1}
        };
        System.out.println(movesToChessboard(board));

        board = new int[][]{
                {0, 1},
                {1, 0}
        };
        System.out.println(movesToChessboard(board));

        board = new int[][]{
                {1, 0},
                {1, 0}
        };
        System.out.println(movesToChessboard(board));
    }

    /**
     * 一个合法的棋盘必须具备如下两个条件：
     * 1）任何棋盘内部的四边形，要么四个角都是1，要么四个角都是0，要么两个0和两个1。
     * 2）每一行和每一列中，0和1的数量都是相等的，假设棋盘是N * N大小的，那么：
     * a）如果N = 2 * K，那么每一行每一列有且仅有K个0和K个1；
     * b）如果N = 2 * K + 1，那么每一行每一列要么有K个1和K+1个0，要么有K+1个1和K个0。
     * 判断出来某个棋盘合法之后，开始计算最小的交换次数。基于上述性质，对第一行进行整理（通过交换列来实现），以N = 5为例来说明：
     * 1）如果N是偶数，计算实现01010和10101需要的最小移动步数，并且取最小者；
     * 2）如果N是奇数，就只有一种取法，所以就取移动步数为偶数的最小次数。
     * 默认 10101 是正确的位置
     * 如果N是奇数，必须得到偶数个，因为之前统计的是跟棋盘位置的错位的个数，而每次交换行或者列，会修改两个错位，如果是奇数就无法还原为棋盘。
     * 举个例子，比如首行是 10001，如果和 10101 比较，只有一个错位，但是无法通过交换得到 10101 ，所以必须要交换得到 01010，
     * 此时的错位是4个，而通过 n - rowDiff 正好也能得到4，这就是为什么需要偶数个错位。
     * 如果n是偶数，那么就不会出现这种问题，但是会出现另一个问题，比如 0101，这本身是正确的棋盘排列，但和 1010 比较，会得到4个错位，
     * 所以应该跟 n - rowDiff 比较取较小值。列的处理跟行的处理完全一样。
     * 最终我们把行错位个数跟列错位个数相加，再除以2，就可以得到最小的交换次数了，因为每交换一次，可以修复两个错位
     */
    public static int movesToChessboard(int[][] board) {
        int n = board.length, rowSum = 0, colSum = 0, rowDiff = 0, colDiff = 0;
        // 判断是否满足条件一
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((board[0][0] ^ board[i][0] ^ board[0][j] ^ board[i][j]) == 1) {
                    return -1;
                }
            }
        }

        // 判断是否满足条件二，同时计算行列错位个数
        for (int i = 0; i < n; ++i) {
            rowSum += board[0][i];
            colSum += board[i][0];
            rowDiff += board[i][0] == i % 2 ? 1 : 0;
            colDiff += board[0][i] == i % 2 ? 1 : 0;
        }
        if (n / 2 > rowSum || rowSum > (n + 1) / 2) {
            return -1;
        }
        if (n / 2 > colSum || colSum > (n + 1) / 2) {
            return -1;
        }

        if (n % 2 == 1) {
            // 奇数情况
            if (colDiff % 2 == 1) {
                colDiff = n - colDiff;
            }
            if (rowDiff % 2 == 1) {
                rowDiff = n - rowDiff;
            }
        } else {
            // 偶数情况
            colDiff = Math.min(n - colDiff, colDiff);
            rowDiff = Math.min(n - rowDiff, rowDiff);
        }
        return (colDiff + rowDiff) / 2;
    }
}
