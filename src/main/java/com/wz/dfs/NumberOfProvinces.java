package com.wz.dfs;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are
 * directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 *
 * Example 1:
 * @link ../../../../resource/NumberOfProvinces1.jpg
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * Example 2:
 * @link ../../../../resource/NumberOfProvinces2.jpg
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 * Constraints:
 * 1. 1 <= n <= 200
 * 2. n == isConnected.length
 * 3. n == isConnected[i].length
 * 4. isConnected[i][j] is 1 or 0.
 * 5. isConnected[i][i] == 1
 * 6. isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {
    public static void main(String[] args) {
        int[][] isConnected = new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println(findCircleNum(isConnected));
    }

    /**
     * DFS
     * 使用 visited 记录每个节点是否已访问，遍历每个节点，如果该节点已访问则直接跳过
     * 否则，将该节点设置为已访问，然后 DFS 将与之相连的节点也设置为已访问，最后结果加 1
     */
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length, result = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(isConnected, i, visited);
            result++;
        }

        return result;
    }

    /**
     * 把所有与 i 相连的 j 设置为已访问，即 visited[j] = true
     */
    private static void dfs(int[][] isConnected, int i, boolean[] visited) {
        for (int j = 0; j < isConnected.length; j++) {
            if (visited[j]) {
                continue;
            }
            if (isConnected[i][j] == 1) {
                visited[j] = true;
                dfs(isConnected, j, visited);
            }
        }
    }
}
