package com.wz.other;

/**
 * There is an undirected star graph consisting of n nodes labeled from 1 to n.
 * A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi.
 * Return the center of the given star graph.
 *
 * Example 1:
 * @see ../../../../resource/FindCenterOfStarGraph.jpg
 * Input: edges = [[1,2],[2,3],[4,2]]
 * Output: 2
 * Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
 *
 * Constraints:
 * 1. 3 <= n <= 10^5
 * 2. edges.length == n - 1
 * 3. edges[i].length == 2
 * 4. 1 <= ui, vi <= n
 * 5. ui != vi
 * 6. The given edges represent a valid star graph.
 */
public class FindCenterOfStarGraph {
    public static void main(String[] args) {
        System.out.println(findCenter(new int[][]{{1, 2}, {2, 3}, {4, 2}}));
    }

    /**
     * 中心节点在每个 edge 中都存在，因此只需要判断前两个 edge，相同的那个就是中心节点
     */
    public static int findCenter(int[][] edges) {
        int first = edges[0][0], second = edges[0][1];
        if (first == edges[1][0] || first == edges[1][1]) {
            return first;
        }
        return second;
    }
}
