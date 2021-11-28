package com.wz.bfs;

import java.util.*;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words,
 * any connected graph without simple cycles is a tree.
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi]
 * indicates that there is an undirected edge between the two nodes ai and bi in the tree,
 * you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Example 1:
 * @link ../../../../resource/MinimumHeightTrees1.jpg
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 *
 * Example 2:
 * @link ../../../../resource/MinimumHeightTrees2.jpg
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 * Constraints:
 * 1. 1 <= n <= 2 * 10^4
 * 2. edges.length == n - 1
 * 3. 0 <= ai, bi < n
 * 4. ai != bi
 * 5. All the pairs (ai, bi) are distinct.
 * 6. The given input is guaranteed to be a tree and there will be no repeated edges.
 */
public class MinimumHeightTrees {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(findMinHeightTrees(6, edges));
    }

    /**
     * 根据题意可以知道，返回的节点的数量必定为 1 或 2。否则的话，假如节点数量为 3，因为输入数据保证该图具有树的特征，
     * 所以这三个点必定相连，则这三个点中必定存在一个节点的高度比其他两个节点的高度要大，所以返回的节点数量不可能大于 2
     * 建立图，从叶节点开始向上遍历，遍历到最后剩余的节点就是结果
     * 叶节点就是度为 1 的节点，
     */
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<Set<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // 叶节点
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> tmp = new ArrayList<>();
            // 从叶节点开始遍历
            for (int leaf : leaves) {
                // 逐层移除叶节点，向上层遍历，使用 tmp 记录这一层的叶节点
                for (int neighbor : graph.get(leaf)) {
                    graph.get(neighbor).remove(leaf);
                    if (graph.get(neighbor).size() == 1) {
                        tmp.add(neighbor);
                    }
                }
            }
            leaves = tmp;
        }

        return leaves;
    }
}
