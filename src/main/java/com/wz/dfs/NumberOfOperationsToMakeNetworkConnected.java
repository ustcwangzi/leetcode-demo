package com.wz.dfs;

/**
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b]
 * represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers,
 * and place them between any pair of disconnected computers to make them directly connected.
 * Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1.
 *
 * Example 1:
 * @link ../../../../resource/NumberOfOperationsToMakeNetworkConnected1.jpg
 * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 * Output: 1
 * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
 *
 * Example 2:
 * @link ../../../../resource/NumberOfOperationsToMakeNetworkConnected2.jpg
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * Output: 2
 *
 * Constraints:
 * 1. 1 <= n <= 10^5
 * 2. 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * 3. connections[i].length == 2
 * 4. 0 <= connections[i][0], connections[i][1] < n
 * 5. connections[i][0] != connections[i][1]
 * 6. There are no repeated connections.
 * 7. No two computers are connected by more than one cable.
 */
public class NumberOfOperationsToMakeNetworkConnected {
    public static void main(String[] args) {
        int[][] connections = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        System.out.println(makeConnected(4, connections));
    }

    /**
     * 并查集 Union-Find
     * 计算联通部分个数 count，多余的边 extraEdge，如果多余的边小于联通部分，返回 -1，否则返回联通的个数减 1
     */
    public static int makeConnected(int n, int[][] connections) {
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        int extraEdge = 0;
        for (int[] edge : connections) {
            int x = getRoot(root, edge[0]), y = getRoot(root, edge[1]);
            if (x == y) {
                // 有公共祖先，说明此条边多余
                extraEdge++;
            } else {
                // 没有公共祖先，将两个点连接起来
                root[x] = y;
            }
        }

        // 计算联通部分个数
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (root[i] == i) {
                count++;
            }
        }

        return extraEdge >= count - 1 ? count - 1 : -1;
    }

    private static int getRoot(int[] root, int i) {
        while (root[i] != i) {
            i = root[i];
        }
        return i;
    }
}
