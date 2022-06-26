package com.wz.dfs;

import java.util.*;

/**
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi]
 * represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 * Return all critical connections in the network in any order.
 *
 * Example 1:
 * @link ../../../../resource/CriticalConnectionsInNetwork.jpg
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 * Example 2:
 * Input: n = 2, connections = [[0,1]]
 * Output: [[0,1]]
 *
 * Constraints:
 * 1. 2 <= n <= 10^5
 * 2. n - 1 <= connections.length <= 10^5
 * 3. 0 <= ai, bi <= n - 1
 * 4. ai != bi
 * 5. There are no repeated connections.
 */
public class CriticalConnectionsInNetwork {
    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));
        System.out.println(criticalConnections(4, connections));
        System.out.println(criticalConnections(2, Collections.singletonList(Arrays.asList(0, 1))));
    }

    /**
     * 其实就是要找到所有不在环上的边，寻找环很容易想到使用 DFS，那么怎么区分在环上 or 不在环上呢，可以对每个节点记录增加 ID
     * 找到环的时候，取环中最小的 ID 作为整个环的 ID，遍历相邻节点时有三种情况：
     * 1. 父节点，无需处理，继续下一个
     * 2. 邻居节点未遍历，DFS 遍历邻居节点
     * 3. 邻居节点已遍历，根据邻居节点 ID 来更新自己的 ID
     * 遍历完相邻节点之后，若 ID 就是父节点告诉自己的那个，说明不在环中，加入结果集
     */
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(connections);
        int[] ids = new int[n];
        Arrays.fill(ids, -1);
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, 0, -1, ids, graph, result);
        return result;
    }

    /**
     * 计算当前节点的 ID，并将不在环上的边加入结果集
     */
    private static int dfs(int curNode, int curId, int parent, int[] ids, Map<Integer, Set<Integer>> graph, List<List<Integer>> result) {
        ids[curNode] = curId;
        for (int neighbor : graph.getOrDefault(curNode, Collections.emptySet())) {
            if (neighbor == parent) {
                continue;
            }

            int neighborId = ids[neighbor];
            if (neighborId == -1) {
                neighborId = dfs(neighbor, curId + 1, curNode, ids, graph, result);
            }
            ids[curNode] = Math.min(ids[curNode], neighborId);
        }
        if (ids[curNode] == curId && parent != -1) {
            result.add(Arrays.asList(parent, curNode));
        }
        return ids[curNode];
    }

    private static Map<Integer, Set<Integer>> buildGraph(List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (List<Integer> connection : connections) {
            graph.putIfAbsent(connection.get(0), new HashSet<>());
            graph.putIfAbsent(connection.get(1), new HashSet<>());
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }
        return graph;
    }
}
