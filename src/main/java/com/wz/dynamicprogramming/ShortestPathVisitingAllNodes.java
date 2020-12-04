package com.wz.dynamicprogramming;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 * Example 1:
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 *
 * Example 2:
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *
 * Note:
 * 1. 1 <= graph.length <= 12
 * 2. 0 <= graph[i].length < graph.length
 */
public class ShortestPathVisitingAllNodes {
    public static void main(String[] args) {
        int[][] graph = new int[4][];
        graph[0] = new int[]{1, 2, 3};
        graph[1] = new int[]{0};
        graph[2] = new int[]{0};
        graph[3] = new int[]{0};

        System.out.println(shortestPathLength(graph));
    }

    /**
     * BFS
     * 对于任意结点i，假如遍历过了，则将其对应位上变为1，即 或 上 1<<i，这样每个结点都可以被分别编码进对应位，
     * 则遍历过n个结点的十进制数就是 2^n-1 了，只要某个状态的十进制数等于 2^n-1，则表示到达了终止状态。
     * 另外，由于最短路径的起点不定，那么这里的 BFS 的起点就应该是所有的结点，将每个结点都当作起始结点，并将结点编号编码到十进制数中，
     * 和当前位置一起组成 pair 放进队列中。将n个起点都放入队列之后，就可以开始遍历了，它们都属于同一层，这里进行的是 BFS 的层序遍历的形式。
     * 对于每个取出的元素，首先判断取出的状态的 pair 的第一个编码十进制数是否等于最终结果值 target，是的话直接返回结果。
     * 然后再根据第二个位置值去 graph 数组中查找所有与其相邻的结点，对于每个相邻的结点 next，由于在之前的基础上又加上了结点 next，这也要编码进去，
     * 所以要 或 上 1<<next，然后在 visted 集合中查找该新状态是否存在，不存在的话加入 visited 集合，并把编码成的十进制数 path
     * 和当前结点编号 next 组成新的 pair 加入队列进行下次遍历。每层遍历结束后记得结果要自增1，while 循环退出后返回 -1，
     * 其实根本不会返回 -1，因为题目中是无向连通图，一定会有经过所有结点的路径存在
     */
    public static int shortestPathLength(int[][] graph) {
        int n = graph.length, target = 0, result = 0;
        Set<String> visited = new HashSet<>(n);
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            int mask = (1 << i);
            target |= mask;
            visited.add(mask + "-" + i);
            queue.add(new Pair<>(mask, i));
        }
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                Pair<Integer, Integer> cur = queue.poll();
                if (cur.getKey() == target) {
                    return result;
                }
                for (int next : graph[cur.getValue()]) {
                    int path = cur.getKey() | (1 << next);
                    String str = path + "-" + next;
                    if (visited.contains(str)) continue;
                    visited.add(str);
                    queue.add(new Pair<>(path, next));
                }
            }
            ++result;
        }
        return -1;
    }
}
