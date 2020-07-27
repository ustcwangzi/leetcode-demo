package com.wz.lists;

/**
 * You are given a map of a server center, represented as a m * n integer matrix grid,
 * where 1 means that on that cell there is a server and 0 means that it is no server.
 * Two servers are said to communicate if they are on the same row or on the same column.
 * Return the number of servers that communicate with any other server.
 *
 * Example 1:
 * Input: grid = [[1,0],[0,1]]
 * Output: 0
 * Explanation: No servers can communicate with others.
 *
 * Example 2:
 * Input: grid = [[1,0],[1,1]]
 * Output: 3
 * Explanation: All three servers can communicate with at least one other server.
 *
 * Example 3:
 * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * Output: 4
 * Explanation: The two servers in the first row can communicate with each other.
 * The two servers in the third column can communicate with each other.
 * The server at right bottom corner can't communicate with any other server.
 */
public class CountServersThatCommunicate {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0}, {0, 1}};
        System.out.println(countServers(grid));

        grid = new int[][]{{1, 0}, {1, 1}};
        System.out.println(countServers(grid));

        grid = new int[][]{{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        System.out.println(countServers(grid));
    }

    /**
     * 如果在一行或一列有两个及两个以上的服务器，则同一行或同一列的服务器都能互相通信
     * 统计每行1的个数和每列1的个数，然后扫描每个元素，如果该元素所在的行或列1的个数大于1，则认为服务器之间可以通信
     */
    public static int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m], col = new int[n];
        // 统计每行每列1的个数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i] += grid[i][j];
                col[j] += grid[i][j];
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 判断是否能够通信
                if (grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) {
                    result++;
                }
            }
        }
        return result;
    }
}
