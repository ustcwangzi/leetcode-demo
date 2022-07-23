package com.wz.other;

/**
 * Alice and Bob have an undirected graph of n nodes and 3 types of edges:
 * - Type 1: Can be traversed by Alice only.
 * - Type 2: Can be traversed by Bob only.
 * - Type 3: Can by traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi,
 * find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob.
 * The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 * Return the maximum number of edges you can remove, or return -1 if it's impossible for the graph to be fully traversed by Alice and Bob.
 *
 * Example 1:
 * @link ../../../../resource/RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable1.jpg
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * Output: 2
 * Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob.
 * Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
 *
 * Example 2:
 * @link ../../../../resource/RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable2.jpg
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * Output: 0
 * Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
 *
 * Example 3:
 * @link ../../../../resource/RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable3.jpg
 * Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * Output: -1
 * Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1.
 * Therefore it's impossible to make the graph fully traversable.
 *
 * Constraints:
 * 1. 1 <= n <= 10^5
 * 2. 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
 * 3. edges[i].length == 3
 * 4. 1 <= edges[i][0] <= 3
 * 5. 1 <= edges[i][1] < edges[i][2] <= n
 * 6. All tuples (typei, ui, vi) are distinct.
 */
public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
    public static void main(String[] args) {
        System.out.println(maxNumEdgesToRemove(4, new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}}));
        System.out.println(maxNumEdgesToRemove(4, new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 4}, {2, 1, 4}}));
    }

    /**
     * 并查集
     * 将边的删除过程转变为图的生成过程，用并查集记录节点间的连通性，若待生成的边所连接的节点先前已被连通，说明该边可删除
     * 类型 3 的边至少可替代 1 条类型 1、2 的边，因此可先对通用的类型 3 的边进行生成，再处理仅可供一人使用的类型 1、2 的边
     */
    public static int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind unionFindA = new UnionFind(n), unionFindB = new UnionFind(n);
        int count = 0, sizeA = n, sizeB = n;
        for (int[] edge : edges) {
            if (edge[0] != 3) {
                continue;
            }
            unionFindA.union(edge[1], edge[2]);
            if (unionFindB.union(edge[1], edge[2])) {
                sizeA--;
                sizeB--;
            } else {
                count++;
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (unionFindA.union(edge[1], edge[2])) {
                    sizeA--;
                } else {
                    count++;
                }
            } else if (edge[0] == 2) {
                if (unionFindB.union(edge[1], edge[2])) {
                    sizeB--;
                } else {
                    count++;
                }
            }
        }
        // 判断是否联通
        return sizeA == 1 && sizeB == 1 ? count : -1;
    }

    private static class UnionFind {
        private final int[] root;

        public UnionFind(int n) {
            this.root = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                root[i] = i;
            }
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            root[rootX] = rootY;
            return true;
        }

        public int find(int x) {
            if (root[x] != x) {
                return root[x] = find(root[x]);
            }
            return x;
        }
    }
}
