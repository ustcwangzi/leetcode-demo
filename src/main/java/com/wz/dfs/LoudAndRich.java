package com.wz.dfs;

import java.util.*;

/**
 * There is a group of n people labeled from 0 to n - 1 where each person has a different amount of money and a different level of quietness.
 * You are given an array richer where richer[i] = [ai, bi] indicates that ai has more money than bi and an integer array quiet where quiet[i] is the quietness of the ith person.
 * All the given data in richer are logically correct (i.e., the data will not lead you to a situation where x is richer than y and y is richer than x at the same time).
 * Return an integer array answer where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y])
 * among all people who definitely have equal to or more money than the person x.
 *
 * Example 1:
 * Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * Output: [5,5,2,5,4,5,6,7]
 * Explanation:
 * answer[0] = 5.
 * Person 5 has more money than 3, which has more money than 1, which has more money than 0.
 * The only person who is quieter (has lower quiet[x]) is person 7, but it is not clear if they have more money than person 0.
 * answer[7] = 7.
 * Among all people that definitely have equal to or more money than person 7 (which could be persons 3, 4, 5, 6, or 7),
 * the person who is the quietest (has lower quiet[x]) is person 7.
 * The other answers can be filled out with similar reasoning.
 *
 * Example 2:
 * Input: richer = [], quiet = [0]
 * Output: [0]
 *
 * Constraints:
 * 1. n == quiet.length
 * 2. 1 <= n <= 500
 * 3. 0 <= quiet[i] < n
 * 4. All the values of quiet are unique.
 * 5. 0 <= richer.length <= n * (n - 1) / 2
 * 6. 0 <= ai, bi < n
 * 7. ai != bi
 * 8. All the pairs of richer are unique.
 * 9. The observations in richer are all logically consistent.
 */
public class LoudAndRich {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(loudAndRich(
                new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}},
                new int[]{3, 2, 5, 4, 6, 1, 7, 0})));
    }

    /**
     * richer[i] = [x, y] 表示 x 比 y 富有，quiet[i] = q 表示 i 的安静程度为 q
     * 求 answer ，其中 answer[x] = y 的前提是，在所有拥有的钱不少于 x 的人中， y 是最安静的人
     * 构建有向图，A->B 表示 B 比 A 富有，即构建一个由钱少流向钱多的有向图
     * 然后 DFS 遍历当前当前节点的邻居节点（邻居都比自己富有），找出最小的安静值，为加快计算，使用 visited 保存已找到的结果
     */
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, Integer> quietMap = new HashMap<>(quiet.length), visited = new HashMap<>();
        for (int i = 0; i < quiet.length; i++) {
            quietMap.put(quiet[i], i);
        }

        Map<Integer, Set<Integer>> graph = buildGraph(richer, quiet);
        int[] result = new int[quiet.length];
        for (int i = 0; i < quiet.length; i++) {
            result[i] = quietMap.get(dfs(i, graph, quiet, visited));
        }
        return result;
    }

    private static int dfs(int index, Map<Integer, Set<Integer>> graph, int[] quiet, Map<Integer, Integer> visited) {
        if (visited.containsKey(index)) {
            return visited.get(index);
        }

        int min = quiet[index];
        // 找出邻居中的最小安静值
        for (int neighbor : graph.get(index)) {
            min = Math.min(min, dfs(neighbor, graph, quiet, visited));
        }
        visited.put(index, min);
        return min;
    }

    private static Map<Integer, Set<Integer>> buildGraph(int[][] richer, int[] quiet) {
        Map<Integer, Set<Integer>> graph = new HashMap<>(quiet.length);
        for (int i = 0; i < quiet.length; i++) {
            graph.put(i, new HashSet<>());
        }
        // 所有邻居都比自己富有
        for (int[] cur : richer) {
            graph.get(cur[1]).add(cur[0]);
        }
        return graph;
    }
}
